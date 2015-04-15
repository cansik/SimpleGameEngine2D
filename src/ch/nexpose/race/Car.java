package ch.nexpose.race;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.GravityObject2D;

import java.awt.*;

/**
 * Created by cansik on 23/09/14.
 */
public class Car extends GravityObject2D
{
    public Car(SimpleGameEngine2D engine)
    {
        super(engine, null);

        //reset texture
        setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/race/car.jpeg")));
        setCounterforce(0.975);
    }
}
