package ch.nexpose.deepspace.objects;

import ch.nexpose.deepspace.stories.LevelGameStory;
import ch.nexpose.deepspace.gui.ScoreType;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.collisions.Collision;
import ch.nexpose.sge.objects.BaseObject2D;
import ch.nexpose.sge.objects.GravityObject2D;

import java.awt.*;

/**
 * Created by cansik on 06/05/14.
 */
public class Bullet extends GravityObject2D
{
    BaseObject2D parent;

    public BaseObject2D getParent()
    {
        return parent;
    }

    public void setParent(BaseObject2D parent)
    {
        this.parent = parent;
    }

    public Bullet(SimpleGameEngine2D engine, BaseObject2D parent)
    {
        super(engine, null);
        this.parent = parent;

        setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/bullet.png")));

        setCounterforce(1);
        setLocation(new Point(parent.getLocation().x + parent.getSize().width, parent.getLocation().y + (int) (parent.getSize().height / 2) - (int) (getSize().height / 2)));

    }

    @Override
    public void action()
    {
        super.action();

        if(!isOnScene())
            setAlive(false);
    }

    @Override
    public void collisionDetected(Collision c)
    {
        BaseObject2D crashedObject = c.getEnemyObject(this);

        if(parent != crashedObject)
        {
            setAlive(false);
            if (crashedObject instanceof EnemySpaceShip)
            {
                EnemySpaceShip enemy = (EnemySpaceShip) crashedObject;
                enemy.hit();
            }

            if(crashedObject instanceof SpaceShip)
            {
                SpaceShip space = (SpaceShip)crashedObject;
                getGameStory().scorePoint(ScoreType.Life);
            }
        }
    }

    /**
     * Returns the current game story.
     * @return
     */
    private LevelGameStory getGameStory()
    {
        return (LevelGameStory)getEngine().getNextFrameListener().get(0);
    }
}
