import javax.swing.*;
import java.util.Objects;

public class Player extends Sprite{
    public Player(){
        w=150;
        h=300;
        x=50;
        y=320;
        image= new ImageIcon(Objects.requireNonNull(Player.class.getResource("player.gif")));
    }

    public void move() {
        x=x+speed;
    }

    public boolean outOfScreen(){
        //return x>1500;
        return x + w > 1500;
    }
}
