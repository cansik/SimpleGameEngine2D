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
    Object2D parent;

    public Object2D getParent()
    {
        return parent;
    }

    public void setParent(Object2D parent)
    {
        this.parent = parent;
    }

    public Bullet(SimpleGameEngine2D engine, Object2D parent)
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
        Object2D crashedObject = c.getEnemyObject(this);
        setAlive(false);

        if(crashedObject instanceof EnemySpaceShip)
        {
            EnemySpaceShip enemy = (EnemySpaceShip)crashedObject;
            enemy.crash();
        }
    }
}
