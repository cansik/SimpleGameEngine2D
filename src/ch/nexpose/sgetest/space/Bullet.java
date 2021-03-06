/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sgetest.space;

import java.awt.*;

import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.collisions.Collision;
import ch.nexpose.sge.objects.BaseObject2D;
import ch.nexpose.sge.objects.MovingObject2D;

/**
 *
 * @author cansik
 */
public class Bullet extends MovingObject2D {
    
    public Bullet(BaseObject2D shooter)
    {
        super(shooter.getEngine());
        this.setColor(Color.yellow);
        this.setSpeed(60);
        this.setSize(new Dimension(10, 10));
        this.setDirection(Direction.RIGHT);
        this.setLocation(new Point(shooter.getLocation().x + shooter.getSize().width, shooter.getCenterLocation().y));
    }
    
    @Override
    public void action()
    {
        super.action();
        
        //check out of bound
        if(this.getLocation().x > this.getEngine().getScene().getViewPortSize().getWidth())
        {
            this.setAlive(false);
        }

        //hitbox upadte
        int rightSpeed = (int)(this.getSpeed() / 2);
        this.setHitbox(new Rectangle(
                new Point(this.getLocation().x - rightSpeed, this.getLocation().y),
                new Dimension(this.getSize().width + rightSpeed, this.getSize().height)
        ));
    }

    @Override
    public void paint(Graphics2D g)
    {
        g.setColor(this.getColor());
        g.fillOval(this.getLocation().x, this.getLocation().y, this.getSize().width, this.getSize().height);
    }

    @Override
    public void collisionDetected(Collision c)
    {
        c.getFirstObject().setAlive(false);
        c.getSecondObject().setAlive(false);
        System.out.println("object killed!");
    }
}
