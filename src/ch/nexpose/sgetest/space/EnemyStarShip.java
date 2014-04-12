package ch.nexpose.sgetest.space;

import ch.nexpose.sge.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.MovingObject2D;
import ch.nexpose.sge.objects.TexturedObject2D;

import java.awt.*;

/**
 * Created by cansik on 09/04/14.
 */
public class EnemyStarShip extends TexturedObject2D
{
    Image enemyImage;

    public EnemyStarShip(SimpleGameEngine2D engine)
    {
        super(engine);

        this.setColor(Color.blue);
        this.setSize(new Dimension(50, 50));
        this.setDirection(Direction.LEFT);
        this.setSpeed(3);
        this.setTexture(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/enemy.png")));
    }

    @Override
    public void action()
    {
        super.action();

        //check out of bound
        if(this.getLocation().x < (0 - this.getSize().width))
        {
            this.setAlive(false);
        }
    }
}
