package ch.nexpose.deepspace.objects;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.GravityObject2D;

import java.awt.*;

/**
 * Created by cansik on 05/05/14.
 */
public class SpaceShip extends GravityObject2D
{
    public SpaceShip(SimpleGameEngine2D engine)
    {
        super(engine, null);
        setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/spaceship.png")));
    }
}
