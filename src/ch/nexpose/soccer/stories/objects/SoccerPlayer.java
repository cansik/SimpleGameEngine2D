package ch.nexpose.soccer.stories.objects;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.collisions.Collision;
import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.objects.GravityObject2D;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 19/06/14.
 */
public class SoccerPlayer extends GravityObject2D
{
    final double PLAYER_SPEED = 0.5;

    boolean selected;

    SoccerBall currentBall;

    public SoccerPlayer(SimpleGameEngine2D engine)
    {
        super(engine, null);
        setSize(new Dimension(30, 30));
        setColor(Color.yellow);
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    @Override
    public void action()
    {
        super.action();

        if(selected)
        {
            //steer
            if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_RIGHT))
                push(PLAYER_SPEED, Direction.RIGHT);

            if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_LEFT))
                push(PLAYER_SPEED, Direction.LEFT);

            if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_UP))
                push(PLAYER_SPEED, Direction.UP);

            if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_DOWN))
                push(PLAYER_SPEED, Direction.DOWN);

            //shoot
            if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_SPACE))
                shootBall();
        }

        if(currentBall != null)
        {
            currentBall.setVelocityX(getVelocityX());
            currentBall.setVelocityY(getVelocityY());
        }
    }

    private void shootBall()
    {
        int impact = 2;
        if(currentBall != null)
        {
            if(getVelocityX() > 0)
                currentBall.push(Math.abs(getVelocityX() * impact), Direction.RIGHT);

            if(getVelocityX() <= 0)
                currentBall.push(Math.abs(getVelocityX() * impact), Direction.LEFT);

            if(getVelocityY() > 0)
                currentBall.push(Math.abs(getVelocityY() * impact), Direction.DOWN);

            if(getVelocityY() <= 0)
                currentBall.push(Math.abs(getVelocityY() * impact), Direction.UP);

            currentBall = null;
        }
    }

    @Override
    public void collisionDetected(Collision c)
    {
        if(c.getEnemyObject(this) instanceof SoccerBall)
            currentBall = (SoccerBall)c.getEnemyObject(this);
    }

    @Override
    public void paint(Graphics2D g)
    {
        //draw oval
        g.setColor(getColor());
        g.fillOval(getLocation().x, getLocation().y,
                getSize().width, getSize().height);


        if(selected)
        {
            g.setStroke(new BasicStroke(3));
            g.setColor(Color.red);
            g.drawOval(getLocation().x, getLocation().y,
                    getSize().width, getSize().height);
        }
    }
}
