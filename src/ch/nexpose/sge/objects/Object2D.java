/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge.objects;

import java.awt.*;

import ch.nexpose.sge.collisions.Collision;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.collisions.CollisionListener;

/**
 *
 * @author cansik
 */
public abstract class Object2D implements CollisionListener
{

    boolean alive;
    Point location;
    Dimension size;
    Rectangle hitbox;
    Color color;
    SimpleGameEngine2D engine;
    boolean collisionable;

    
    public Object2D(SimpleGameEngine2D engine)
    {
        this.alive = true;
        this.collisionable = true;
        this.location = new Point(0, 0);
        this.size = new Dimension(1, 1);
        this.hitbox = null;
        this.color = Color.white;
        this.engine = engine;
    }

    public Rectangle getHitbox()
    {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox)
    {
        this.hitbox = hitbox;
    }

    public boolean isCollisionable()
    {
        return collisionable;
    }

    public void setCollisionable(boolean collisionable)
    {
        this.collisionable = collisionable;
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
    
    public void paint(Graphics2D g)
    {
        g.setColor(color);
        g.drawRect(this.getLocation().x, this.getLocation().y, this.getSize().width, this.getSize().height);
    }
    
    public Collision detectCollision(Object2D object)
    {
        Collision c = null;
        Rectangle localHitbox = new Rectangle(this.getLocation(), this.getSize());
        Rectangle objectHitbox = new Rectangle(object.getLocation(), object.getSize());

        if(hitbox != null)
            localHitbox = this.hitbox;

        if(object.hitbox != null)
            objectHitbox = object.hitbox;
        
        if(localHitbox.x < objectHitbox.x + objectHitbox.width  &&
                objectHitbox.x < localHitbox.x + localHitbox.width  &&
                localHitbox.y < objectHitbox.y + objectHitbox.height &&
                objectHitbox.y < localHitbox.y + localHitbox.height)
        {
            c = new Collision(this, object);
        }
        
        return c;
    }

    public void center()
    {
        this.setLocation(new Point(((int)engine.getScene().getViewPortSize().getWidth() / 2) - (int)(this.getSize().width / 2),
                (int)((int)engine.getScene().getViewPortSize().getHeight() / 2) - (int)(this.getSize().height / 2)));
    }


    public void action() {};

    public void collisionDetected(Collision c) {};
}
