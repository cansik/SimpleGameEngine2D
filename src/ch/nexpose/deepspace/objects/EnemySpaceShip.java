package ch.nexpose.deepspace.objects;

import ch.nexpose.deepspace.LevelGameStory;
import ch.nexpose.deepspace.gui.ScoreType;
import ch.nexpose.sge.fx.Animation;
import ch.nexpose.sge.controls.Direction;
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
    boolean canShoot;

    public EnemySpaceShip(SimpleGameEngine2D engine, boolean canShoot, boolean isSpeeding)
    {
        super(engine, null);
        setCounterforce(1);
        this.canShoot = canShoot;

        if(canShoot)
            setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/enemyspaceship_shooting.png")));
        else if(isSpeeding)
        {
            setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/enemyspaceship_speeding.png")));
            //Todo: just a workaround, use default speed of spaceship
            this.push(2, Direction.LEFT);
        }
        else
            setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/enemyspaceship.png")));

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
            if(canShoot)
            {
                //shoot back
                Bullet b = new Bullet(getEngine(), this);
                b.push(12, Direction.LEFT);

                getEngine().addGameObject(b);
            }
        }

        super.action();
    }

}
