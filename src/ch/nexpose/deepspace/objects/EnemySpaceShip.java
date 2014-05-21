package ch.nexpose.deepspace.objects;

import ch.nexpose.deepspace.LevelGameStory;
import ch.nexpose.sge.Animation;
import ch.nexpose.sge.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.GravityObject2D;
import ch.nexpose.sgetest.space.RandomGenerator;

import java.awt.*;

/**
 * Created by cansik on 09/05/14.
 */
public class EnemySpaceShip extends GravityObject2D
{
    boolean isCrashing;
    int animationCounter = 0;

    public EnemySpaceShip(SimpleGameEngine2D engine)
    {
        super(engine, null);
        setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/enemyspaceship.png")));
        setCounterforce(1);

        Animation shootAnimation = new Animation();
        shootAnimation.getFrames().add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/enemyspaceship_crash1.png")));
        shootAnimation.getFrames().add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/enemyspaceship_crash2.png")));
        shootAnimation.getFrames().add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/enemyspaceship_crash3.png")));
        shootAnimation.getFrames().add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/enemyspaceship_crash4.png")));
        shootAnimation.getFrames().add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/enemyspaceship_crash5.png")));
        shootAnimation.getFrames().add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/enemyspaceship_crash6.png")));
        this.setAnimation(shootAnimation);
    }

    public void crash()
    {
        playAnimation();
        isCrashing = true;
        setCollisionable(false);
        setCounterforce(0.95);

        getGameStory().scorePoint(ScoreType.Kill);
    }

    public void shoot()
    {
        this.playAnimation();

        Bullet b = new Bullet(getEngine(), this);
        b.push(24, Direction.LEFT);
        b.setCounterforce(1);
        b.setLocation(new Point(this.getLocation().x, this.getLocation().y + (int)(this.getSize().height / 2) - (int)(b.getSize().height / 2)));

        getEngine().addGameObject(b);
    }

    private LevelGameStory getGameStory()
    {
        return (LevelGameStory)getEngine().getGameStories().get(0);
    }

    @Override
    public void action()
    {
        //While crashing
        if(isCrashing)
        {
            //fixed flicker bug while crashing
            if(!isAnimated() || animationCounter == getAnimation().getFrames().size() - 1)
                this.setAlive(false);

            animationCounter++;
        }

        //Check out of scene
        if(this.getLocation().x < (0 - this.getSize().width))
        {
            this.setAlive(false);
            getGameStory().scorePoint(ScoreType.Life);
        }

        //Shoot
        if(RandomGenerator.randInt(0, 150) == 1 && !isCrashing)
        {
            //shoot back
            Bullet b = new Bullet(getEngine(), this);
            b.push(12, Direction.LEFT);

            getEngine().addGameObject(b);
        }

        super.action();
    }

}
