package ch.nexpose.sgetest.space;

import ch.nexpose.sge.SimpleGameEngine2D;

import java.awt.*;

/**
 * Created by cansik on 09/04/14.
 */
public class AnimatedStarship extends Starship
{
    public AnimatedStarship(SimpleGameEngine2D engine)
    {
        super(engine);
    }

    @Override
    public void move()
    {
        super.move();
        if(getLocation().x > this.getEngine().getScene().getWidth())
        {
            setLocation(new Point(0 - getSize().width, getLocation().y));
        }
    }
}
