/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sgetest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import ch.nexpose.sge.Direction;
import ch.nexpose.sge.objects.MovingObject2D;
import ch.nexpose.sge.objects.Object2D;

/**
 *
 * @author cansik
 */
public class Bullet extends MovingObject2D {
    
    public Bullet(Object2D shooter)
    {
        super();
        this.setColor(Color.yellow);
        this.setSpeed(60);
        this.setSize(new Dimension(10, 10));
        this.setDirection(Direction.RIGHT);
        this.setLocation(new Point(shooter.getLocation().x + shooter.getSize().width, shooter.getLocation().y));
    }
    
    @Override
    public void move()
    {
        super.move();
        
        //check out of bound
        if(this.getLocation().x > this.getEngine().getScene().getWidth())
        {
            this.setAlive(false);
        }
    }
}
