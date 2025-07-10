import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Enemy extends Sprite{
    public Enemy(int x , int speed){
        this.speed=speed;
        this.x=x;
        y=35;
        w=150;
        h=150;
        image = new ImageIcon(Objects.requireNonNull(Enemy.class.getResource("spider.gif")));
    }
    public void move(){
        if(y>900){
            y=0;
        }
        y=y+speed;
    }
}
