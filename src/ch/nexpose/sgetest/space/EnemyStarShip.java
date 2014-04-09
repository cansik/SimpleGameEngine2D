package ch.nexpose.sgetest.space;

import ch.nexpose.sge.Direction;
import ch.nexpose.sge.objects.MovingObject2D;

import java.awt.*;

/**
 * Created by cansik on 09/04/14.
 */
public class EnemyStarShip extends MovingObject2D
{
    Image enemyImage;

    public EnemyStarShip()
    {
        this.setColor(Color.blue);
        this.setSize(new Dimension(50, 50));
        this.setDirection(Direction.LEFT);
        this.setSpeed(3);
        enemyImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/enemy.png"));
    }

    @Override
    public void paint(Graphics2D g)
    {
        g.drawImage(enemyImage, this.getLocation().x, this.getLocation().y, null);
    }

    @Override
    public void move()
    {
        super.move();

        //check out of bound
        if(this.getLocation().x < (0 - this.getSize().width))
        {
            //this.setAlive(false);
        }
    }
}
