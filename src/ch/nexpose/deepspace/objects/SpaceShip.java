package ch.nexpose.deepspace.objects;

import ch.nexpose.sge.Animation;
import ch.nexpose.sge.Direction;
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

        //prepare animation
        Animation shootAnimation = new Animation();
        shootAnimation.getFrames().add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/spaceship_shot1.png")));
        shootAnimation.getFrames().add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/spaceship_shot2.png")));
        shootAnimation.getFrames().add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/spaceship_shot3.png")));
        this.setAnimation(shootAnimation);

        this.center();
        this.setLocation(new Point(0, this.getLocation().y));
        this.setBordercheck(true);
    }

    public void shoot()
    {
        this.playAnimation();

        Bullet b = new Bullet(getEngine(), this);
        b.push(48, Direction.RIGHT);

        getEngine().addGameObject(b);
    }
}
