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
 * Base game object which implements all the stuff of every game object.
 * @author cansik
 */
public abstract class BaseObject2D implements CollisionListener
{

    boolean alive;
    Point location;
    Dimension size;
    Rectangle hitbox;
    Color color;
    SimpleGameEngine2D engine;
    boolean collisionable;

    
    public BaseObject2D(SimpleGameEngine2D engine)
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

    /**
     * Returns the center location of the object (default x+w / 2, y+h / 2).
     * @return
     */
    public Point getCenterLocation()
    {
        Point l = getLocation();
        Dimension s = getSize();
        return new Point(l.x + (int)(s.width / 2), l.y + (int)(s.height / 2));
    }

    /**
     * Paints the graphics onto the frame.
     * @param g
     */
    public void paint(Graphics2D g)
    {
        g.setColor(getColor());
        g.drawRect(this.getLocation().x, this.getLocation().y, this.getSize().width, this.getSize().height);
    }

    /**
     * Algorithm to detect, if the object has hit another object.
     * @param object
     * @return
     */
    public Collision detectCollision(BaseObject2D object)
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

    /**
     * Centers the object on the screen by x and y.
     */
    public void centerOnScene()
    {
        this.setLocation(new Point(((int)engine.getScene().getViewPortSize().getWidth() / 2) - (int)(this.getSize().width / 2),
                (int)((int)engine.getScene().getViewPortSize().getHeight() / 2) - (int)(this.getSize().height / 2)));
    }

    /**
     * Implement your game object logic here.
     */
    public void action() {};

    /**
     * Fires if the object was hit by another object.
     * @param c
     */
    public void collisionDetected(Collision c) {};
}
