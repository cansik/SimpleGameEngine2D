package ch.nexpose.sgetest.gravity;

import ch.nexpose.sge.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.AnimatedObject2D;

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
}
