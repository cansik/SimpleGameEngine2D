package ch.nexpose.soccer.stories.objects;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.TexturedObject2D;

import java.awt.*;

/**
 * Created by cansik on 19/06/14.
 */
public class SoccerField extends TexturedObject2D
{
    public SoccerField(SimpleGameEngine2D engine)
    {
        super(engine, null);
        setCollisionable(false);

        //reset texture
        setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/soccer/field.png")));
    }
}