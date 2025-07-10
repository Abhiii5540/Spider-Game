import javax.swing.*;
import java.awt.*;

public abstract class Sprite {
    // this class contains common code for player and enemy
    public int  speed;
    public int x;
    public int y;
    public int w;
    public int h;
    public ImageIcon image;

    public void draw(Graphics pen){
        pen.drawImage(image.getImage(),x,y,w,h,null);
    }
    //Java’s Rectangle class, which gives more accurate hitboxes:
    public Rectangle getHitbox(int offsetX, int offsetY, int reduceW, int reduceH) {
        return new Rectangle(x + offsetX, y + offsetY, w - reduceW, h - reduceH);
    }


}
