package ch.nexpose.sgetest.gravity;

import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.collisions.Collision;
import ch.nexpose.sge.objects.GravityObject2D;
import ch.nexpose.sge.objects.Object2D;
import ch.nexpose.sgetest.space.RandomGenerator;

import java.awt.*;

/**
 * Created by cansik on 25/04/14.
 */
public class GravityBall extends GravityObject2D
{
    final int GROWTH_INDEX = 10;
    final int SPEED_INDEX = 1;

    public GravityBall(SimpleGameEngine2D engine, Image texture)
    {
        super(engine, texture);
        this.setCounterforce(0.9999);

        int rnd1 = RandomGenerator.randInt(0, 3);
        int rnd2 = RandomGenerator.randInt(0, 3);

        this.push(RandomGenerator.randInt(0, 3), Direction.values()[rnd1]);
        this.push(RandomGenerator.randInt(0, 3), Direction.values()[rnd2]);

        this.setLocation(new Point(
                RandomGenerator.randInt(0, engine.getScene().getWidth()),
                RandomGenerator.randInt(0, engine.getScene().getHeight())));

        this.setSize(new Dimension(50, 50));

        this.setBordercheck(true);
    }

    @Override
    public void action()
    {

        super.action();
    }

    @Override
    public void collisionDetected(Collision c)
    {
       onCollisionEat(c);

    };

    private void onCollisionEat(Collision c)
    {
        Object2D winner;
        Object2D loser;

        if(c.getSecondObject().getSize().width < c.getFirstObject().getSize().width)
        {
            winner = c.getFirstObject();
            loser = c.getSecondObject();
        }
        else
        {
            loser = c.getFirstObject();
            winner = c.getSecondObject();
        }

        loser.setAlive(false);
        winner.setSize(new Dimension(
                winner.getSize().width + (int)(loser.getSize().width * 0.3),
                winner.getSize().height + (int)(loser.getSize().height * 0.3)));
    }

    @Override
    public boolean isOnScene()
    {
        //check bounds
        Point futureLocation = this.getNextLocation();
        Dimension sceneSize = this.getEngine().getScene().getViewPortSize();

        if(futureLocation.x < 0)
        {
            this.setVelocityX(getVelocityX() * (-1));
        }

        if(futureLocation.y < 0)
        {
            this.setVelocityY(getVelocityY() * (-1));
        }

        if(futureLocation.x + this.getSize().width > sceneSize.width)
        {
            this.setVelocityX(getVelocityX() * (-1));
        }

        if(futureLocation.y + this.getSize().height > sceneSize.height)
        {
            this.setVelocityY(getVelocityY() * (-1));
        }

        return true;
    }
}
