package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tower extends Ennemi {
    public int timer = 2 * 1000;
    public long tictacOn;
    private BufferedImage tower;

    public Tower(int X, int Y, HitBox hb) {
        super(X, Y, hb);

        try {
            this.tower = ImageIO.read(getClass().getResource("/images/Tower.png"));
        } catch (IOException e) {
            System.out.println("no Image");
        }

    }

    public void attack() {

    }

    @Override
    public void activate() {
        this.notifyKillableObserver();

    }

    @Override
    public void activate(int i) {
        this.notifyKillableObserver();

    }

    @Override
    public BufferedImage getImage() {
        return this.tower;
    }

    public int getTimer() {
        return timer;
    }
}