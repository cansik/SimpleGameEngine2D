/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge.collisions;

import ch.nexpose.sge.objects.BaseObject2D;

import java.awt.Point;

/**
 * Represents a collision of two objects.
 * @author cansik
 */
public class Collision {
    Point location;
    BaseObject2D firstObject;
    BaseObject2D secondObject;

    public Point getLocation()
    {
        return location;
    }

    public BaseObject2D getFirstObject()
    {
        return firstObject;
    }

    public BaseObject2D getSecondObject()
    {
        return secondObject;
    }

    public Collision(BaseObject2D firstObject, BaseObject2D secondObject)
    {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }
    
    public Collision(BaseObject2D firstObject, BaseObject2D secondObject, Point location)
    {
        this(firstObject, secondObject);
        this.location = location;
    }

    /**
     * Returns the other object of the collision.
     * @param mainObject
     * @return
     */
    public BaseObject2D getEnemyObject(BaseObject2D mainObject)
    {
        if(firstObject.equals(mainObject))
            return secondObject;
        else
            return firstObject;
    }
}
