/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge.collisions;

import ch.nexpose.sge.objects.Object2D;
import java.awt.Point;

/**
 *
 * @author cansik
 */
public class Collision {
    Point location;
    Object2D firstObject;
    Object2D secondObject;

    public Point getLocation()
    {
        return location;
    }

    public Object2D getFirstObject()
    {
        return firstObject;
    }

    public Object2D getSecondObject()
    {
        return secondObject;
    }

    public Collision(Object2D firstObject, Object2D secondObject)
    {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }
    
    public Collision(Object2D firstObject, Object2D secondObject, Point location)
    {
        this(firstObject, secondObject);
        this.location = location;
    }

    public Object2D getEnemyObject(Object2D mainObject)
    {
        if(firstObject.equals(mainObject))
            return secondObject;
        else
            return firstObject;
    }
}
