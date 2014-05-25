package ch.nexpose.sge.objects;

import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;

import java.awt.*;

/**
 * Created by cansik on 18/04/14.
 */
public class GravityObject2D extends AnimatedObject2D
{
    final double GRAVITY_ZERO = 1E-1;

    double velocityX = 0;
    double velocityY = 0;

    double counterforce = 0.9;

    public GravityObject2D(SimpleGameEngine2D engine, Image texture)
    {
        super(engine, texture);
    }

    public double getCounterforce()
    {
        return counterforce;
    }

    public void setCounterforce(double counterforce)
    {
        this.counterforce = counterforce;
    }

    public double getVelocityX()
    {
        return velocityX;
    }

    public void setVelocityX(double velocityX)
    {
        this.velocityX = velocityX;
    }

    public double getVelocityY()
    {
        return velocityY;
    }

    public void setVelocityY(double velocityY)
    {
        this.velocityY = velocityY;
    }

    /**
     * Push the object into a specific direction.
     * @param impact
     * @param direction
     */
    public void push(double impact, Direction direction)
    {
        switch (direction) {
            case LEFT:
                velocityX += -impact;
                break;
            case RIGHT:
                velocityX += impact;
                break;
            case UP:
                velocityY += -impact;
                break;
            case DOWN:
                velocityY += impact;
                break;
            case NONE:
                //nothing
                break;
        }
    }

    @Override
    public Point getNextLocation()
    {
        Point nextLocation = new Point(
                (int)Math.round(this.getLocation().x + velocityX),
                (int)Math.round(this.getLocation().y + velocityY));

        //System.out.println("Velocity: " + velocityX + " ; " + velocityY);

        //calc counterforce
        velocityX *= counterforce;
        velocityY *= counterforce;


        if(Math.abs(velocityX) < GRAVITY_ZERO)
            velocityX = 0;

        if(Math.abs(velocityY) < GRAVITY_ZERO)
            velocityY = 0;

        return nextLocation;
    }

    @Override
    public void putOnScene()
    {
        super.putOnScene();

        Dimension sceneSize = this.getEngine().getScene().getViewPortSize();

        if(getLocation().x <= 0)
            velocityX = 0;

        if(getLocation().y <= 0)
            velocityY = 0;

        if(getLocation().x + this.getSize().width >= sceneSize.width)
            velocityX = 0;

        if(getLocation().y + this.getSize().height >= sceneSize.height)
            velocityY = 0;
    }
}
