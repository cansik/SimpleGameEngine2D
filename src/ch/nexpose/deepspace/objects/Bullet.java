package ch.nexpose.deepspace.objects;

import ch.nexpose.sge.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.collisions.Collision;
import ch.nexpose.sge.objects.GravityObject2D;
import ch.nexpose.sge.objects.Object2D;
import ch.nexpose.sgetest.space.RandomGenerator;

import java.awt.*;

/**
 * Created by cansik on 06/05/14.
 */
public class Bullet extends GravityObject2D
{
    public Bullet(SimpleGameEngine2D engine)
    {
        super(engine, null);
        setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/bullet.png")));
    }

    @Override
    public void action()
    {
        super.action();

        if(!isOnScene())
            setAlive(false);
    }

    @Override
    public void collisionDetected(Collision c)
    {
        if(c.getFirstObject() instanceof SpaceShip || c.getSecondObject() instanceof SpaceShip)
        {
            Object2D ship = c.getFirstObject() instanceof SpaceShip ? c.getFirstObject() : c.getSecondObject();

            if(this.getLocation().getX() > ship.getLocation().x)
            {
                this.push(5, Direction.DOWN);
            }

            if(this.getLocation().getX() < ship.getLocation().x)
            {
                this.push(5, Direction.UP);
            }

            if(this.getLocation().getY() > ship.getLocation().y)
            {
                this.push(5, Direction.RIGHT);
            }

            if(this.getLocation().getY() < ship.getLocation().y)
            {
                this.push(5, Direction.LEFT);
            }
        }
    }
}
