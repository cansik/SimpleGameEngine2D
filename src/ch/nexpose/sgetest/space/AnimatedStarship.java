package ch.nexpose.sgetest.space;

import java.awt.*;

/**
 * Created by cansik on 09/04/14.
 */
public class AnimatedStarship extends Starship
{
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
