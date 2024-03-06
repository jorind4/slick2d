package at.sma.game.easygame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.Random;

public class MeinUfo extends SpielObjekt{

    public float geschwindigkeit=2;
    private float acceleration = 0.005f;

    private Rectangle shape;
    public MeinUfo(int x, int y, Image image) {
        super(x, y, image);
        setRandomPosition();
        shape = new Rectangle(x,y, image.getWidth(), image.getHeight());
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void draw(Graphics g) {
        this.getImage().drawCentered(this.getX(),this.getY());
    }

    @Override
    public void update(int delta) {
        if (this.getY() >768) {
            this.setY(0-this.getX());
        }
        this.setY(this.getY()+8);
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());
    }

    public void setRandomPosition() {
        Random r = new Random();
        int ry =0;
        int rx =0;
        rx = r.nextInt(1024-this.getWidth()+1-0) +(this.getWidth()/2);
        ry = r.nextInt(600+1+this.getHeigth())+this.getHeigth();
        this.setY(-ry);
        this.setX(rx);
        setRandomAcceleration();
    }

    private void setRandomAcceleration(){
        Random r = new Random();
        this.geschwindigkeit = r.nextInt(4-1)+1;
    }
}
