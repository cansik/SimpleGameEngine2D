package ch.nexpose.soccer.stories.objects;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.collisions.Collision;
import ch.nexpose.sge.objects.GravityObject2D;

import java.awt.*;

/**
 * Created by cansik on 19/06/14.
 */
public class SoccerBall extends GravityObject2D
{
    public SoccerBall(SimpleGameEngine2D engine)
    {
        super(engine, null);

        //reset texture
        setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/soccer/ball.png")));
        setCounterforce(0.975);
    }

    @Override
    public void action()
    {
        super.action();
        if(!isOnScene())
            setLocation(new Point(300, 300));
    }
}
