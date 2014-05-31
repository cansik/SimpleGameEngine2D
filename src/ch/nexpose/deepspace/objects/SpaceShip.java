package ch.nexpose.deepspace.objects;

import ch.nexpose.deepspace.stories.LevelGameStory;
import ch.nexpose.deepspace.gui.ScoreType;
import ch.nexpose.sge.collisions.Collision;
import ch.nexpose.sge.fx.Animation;
import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.fx.audio.SoundPlayer;
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

        this.centerOnScene();
        this.setLocation(new Point(0, this.getLocation().y));
        this.setBordercheck(true);
    }

    /**
     * Shoot a shot from the spaceship.
     */
    public void shoot()
    {
        this.playAnimation();
        SoundPlayer.playSound(getClass().getResourceAsStream("/resources/sounds/laser_shot.wav"));

        Bullet b = new Bullet(getEngine(), this);
        b.push(48, Direction.RIGHT);

        getEngine().addGameObject(b);
    }

    /**
     * Returns the current game story.
     * @return
     */
    private LevelGameStory getGameStory()
    {
        return (LevelGameStory)getEngine().getNextFrameListener().get(0);
    }

    @Override
    public void collisionDetected(Collision c)
    {
        if(c.getEnemyObject(this) instanceof  EnemySpaceShip)
        {
            EnemySpaceShip enemy = (EnemySpaceShip)c.getEnemyObject(this);
            enemy.crash();

            getGameStory().scorePoint(ScoreType.Life);
        }
    }
}
