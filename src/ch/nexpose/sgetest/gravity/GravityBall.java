package ch.nexpose.sgetest.gravity;

import ch.nexpose.sge.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.collisions.Collision;
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
        this.setCounterforce(1);

        int rndDir = RandomGenerator.randInt(0, 4);
        this.push(SPEED_INDEX, Direction.values()[rndDir]);

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
                winner.getSize().width + (int)(loser.getSize().width * 0.2),
                winner.getSize().height + (int)(loser.getSize().height * 0.2)));
    };
}
