package ch.nexpose.sgetest.jump;

import ch.nexpose.sge.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.AnimatedObject2D;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 17/04/14.
 */
public class Player extends AnimatedObject2D
{

    double velocity = 0.9;
    double realspeed = 5;

    boolean isInJump = false;
    Point jumpLocation;

    public Player(SimpleGameEngine2D engine)
    {
        super(engine, null);
        this.setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/player.png")));
        this.setSize(new Dimension(45, 36));
        this.setLocation(new Point(50, 400));
        this.setSpeed(5);
        jumpLocation = this.getLocation();
        this.setBordercheck(true);
    }

    @Override
    public void action()
    {
        jumpcheck();

        this.getDirections().remove(Direction.LEFT);
        this.getDirections().remove(Direction.RIGHT);

        if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_LEFT))
        {
            this.getDirections().add(Direction.LEFT);
            super.action();
        }

        if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_RIGHT))
        {
            this.getDirections().add(Direction.RIGHT);
            super.action();
        }

        super.action();
    }

    public void jumpcheck()
    {
        if(this.getLocation().y > jumpLocation.y)
        {
            this.getDirections().remove(Direction.DOWN);
            this.setLocation(new Point(this.getLocation().x, jumpLocation.y));
            this.isInJump = false;
            this.realspeed = 5;
        }

        if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_UP))
        {
            if(!isInJump)
            {
                //start jump
                jumpLocation = this.getLocation();
                isInJump = true;
                this.velocity = 0.9;
                this.realspeed = 20;
                this.getDirections().add(Direction.UP);
                realspeed =  realspeed * velocity;
                this.setSpeed((int)realspeed);
            }
        }

        if(isInJump)
        {
            //Speeding down
            realspeed =  realspeed * velocity;
            this.setSpeed((int)realspeed);

            //speeding up
            if(realspeed < 2.0)
            {
                velocity = 1.1;
                this.getDirections().remove(Direction.UP);
                this.getDirections().add(Direction.DOWN);
            }
        }
    }
}
