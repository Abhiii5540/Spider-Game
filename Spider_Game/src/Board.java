import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Board extends JPanel {
    Timer timer;
    BufferedImage backgroundImage;
    Player player;
    Enemy[] enemies = new Enemy[3];

    public Board() {
        setSize(1500 , 920);
        loadBackgroundImage();
        player = new Player();
        loadEnemies();
        gameLoop();
        bindEvents();
        setFocusable(true);
    }

    private void gameOver(Graphics pen){
        if(player.outOfScreen()){
            pen.setFont(new Font("times" , Font.BOLD , 40));
            pen.setColor(Color.red);
            pen.drawString("You Won" , 1500/2 ,920/2);
            timer.stop();
            return;
        }
        for (Enemy enemy : enemies){
            if(isCollide(enemy)){
                pen.setFont(new Font("times" , Font.BOLD , 40));
                pen.setColor(Color.red);
                pen.drawString("Game Over" , 1500/2 ,920/2);
                timer.stop();
            }
        }
    }

    // commenting this as this logic was giving game over even
    // when there is visible gap between player and spider
//    private boolean isCollide(Enemy enemy){
//        int xDistance = Math.abs(player.x - enemy.x);
//        int yDistance = Math.abs(player.y - enemy.y);
//        int maxH = Math.max(player.h,enemy.h);
//        int maxW = Math.max(player.w,enemy.w);
//        return xDistance <= maxW-80 && yDistance <= maxH-80;
//    }
        private boolean isCollide(Enemy enemy) {
            Rectangle playerHitbox = player.getHitbox(20, 20, 40, 40);
            Rectangle enemyHitbox = enemy.getHitbox(10, 10, 20, 20);
            return playerHitbox.intersects(enemyHitbox);
        }

    private void bindEvents(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.speed = 5;
                }
                else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    player.speed = -10;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.speed=0;
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
    }
    private void loadEnemies(){
        int x = 350;
        int gap = 350;
        int speed = 3;
            for (int i = 0; i < enemies.length; i++) {
                enemies[i] = new Enemy(x,speed);
                x = x+gap;
                speed=speed+2;
        }
    }

    private void gameLoop(){
        timer = new Timer(50,(e)->repaint());
        timer.start();
    }

    private void loadBackgroundImage(){
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(Board.class.getResource("Spider_game.jpg")));
        } catch (IOException e) {
            System.out.println("Background image not found");
        e.printStackTrace();
        }
    }

    private void printEnemies(Graphics pen){
        for(Enemy enemy : enemies){
            enemy.draw(pen);
            enemy.move();
        }
    }

    @Override
    public void paintComponent(Graphics pen){
        super.paintComponent(pen); //clean up agar phele se kch h toh
        //all painting logic will be here
        pen.drawImage(backgroundImage,0,0,1500,920,null);
        player.draw(pen);
        player.move();
        printEnemies(pen);
        gameOver(pen);
    }
}
