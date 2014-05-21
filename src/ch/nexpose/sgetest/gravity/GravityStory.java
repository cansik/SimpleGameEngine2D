package ch.nexpose.sgetest.gravity;

import ch.nexpose.sge.Direction;
import ch.nexpose.sge.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.GravityObject2D;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 18/04/14.
 */
public class GravityStory implements IGameStory
{
    SimpleGameEngine2D engine;
    GravityObject2D p;


    public GravityStory(SimpleGameEngine2D engine)
    {
        this.engine = engine;
    }

    @Override
    public void nextFrame()
    {
        double speed = 1;

        if(engine.getInputTracker().isKeyPressed(KeyEvent.VK_RIGHT))
            p.push(speed, Direction.RIGHT);

        if(engine.getInputTracker().isKeyPressed(KeyEvent.VK_LEFT))
            p.push(speed, Direction.LEFT);

        if(engine.getInputTracker().isKeyPressed(KeyEvent.VK_UP))
            p.push(speed, Direction.UP);

        if(engine.getInputTracker().isKeyPressed(KeyEvent.VK_DOWN))
            p.push(speed, Direction.DOWN);
    }

    @Override
    public void runStory()
    {
        p = new GravityObject2D(engine, Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ball.png")));

        p.setLocation(new Point(200, 300));
        p.setSize(new Dimension(50, 50));

        for(int i = 0; i < 15; i++)
        {
            engine.addGameObject(new GravityBall(engine, Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ballblue.png"))));
        }

        engine.addGameObject(p);
        engine.addGameStory(this);
        engine.startEngine();
    }

    @Override
    public void resumeStory()
    {
        engine.startEngine();
    }

    @Override
    public void stopStory()
    {
        engine.stopEngine();
    }
}
