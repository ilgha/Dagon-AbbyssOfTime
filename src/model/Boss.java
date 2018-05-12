package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boss extends Ennemi {
    private BufferedImage Boss;
    private int a;


    public Boss(int X, int Y, HitBox hb) {
        super(X, Y, hb);



        try {
            this.Boss = ImageIO.read(getClass().getResource("/images/Dagon.png"));
        } catch (IOException e) {
            System.out.println("no Image");
        }

    }

    @Override
    public void activate() {
        // TODO Auto-generated method stub

    }
 
    public void move(double d) {
        this.a+=d;
    this.posY=(int) (this.posY+400*Math.sin(a));
    }


    @Override
    public BufferedImage getImage() {

        return Boss;
    }

}