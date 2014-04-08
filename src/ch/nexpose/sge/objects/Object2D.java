/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import ch.nexpose.sge.Collision;
import ch.nexpose.sge.SimpleGameEngine2D;

/**
 *
 * @author cansik
 */
public abstract class Object2D {

    boolean alive;
    Point location;
    Dimension size;
    Color color;
    SimpleGameEngine2D engine;
    
    public Object2D()
    {
        this.alive = true;
        this.location = new Point(0, 0);
        this.size = new Dimension(1, 1);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    public SimpleGameEngine2D getEngine() {
        return engine;
    }

    public void setEngine(SimpleGameEngine2D engine) {
        this.engine = engine;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Point getCenterLocation()
    {
        return new Point(location.x + (int)(size.width / 2), location.y + (int)(size.height / 2));
    }
    
    public void move()
    {
    }
    
    public void paint(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(this.getLocation().x, this.getLocation().y, this.getSize().width, this.getSize().height);
    }
    
    public Collision detectCollision(Object2D object)
    {
        Collision c = null;
        
        if(this.getLocation().x < object.getLocation().x + object.getSize().width  &&
            object.getLocation().x < this.getLocation().x + this.getSize().width  &&
            this.getLocation().y < object.getLocation().y + object.getSize().height &&
            object.getLocation().y < this.getLocation().y + this.getSize().height)
        {
            c = new Collision(this, object);
        }
        
        return c;
    }
}
