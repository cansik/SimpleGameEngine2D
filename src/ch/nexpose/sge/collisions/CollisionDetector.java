/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge.collisions;

import ch.nexpose.sge.objects.Object2D;
import java.util.ArrayList;

/**
 *
 * @author cansik
 */
public class CollisionDetector {

    public void detectCollisions(ArrayList<Object2D> objects)
    {
        for(int c = 0; c < objects.size(); c++)
        {
            for(int i = c + 1; i < objects.size(); i++)            
            {
                if((objects.get(c).isCollisionable() && objects.get(i).isCollisionable()) &&
                        objects.get(c).isAlive() && objects.get(i).isAlive())
                {
                   Collision col = objects.get(c).detectCollision(objects.get(i));
                   if(col != null)
                   {
                       //Event to Objects
                       objects.get(c).collisionDetected((col));
                       objects.get(i).collisionDetected((col));
                   }
                }
            }
        }
    }
    
}
