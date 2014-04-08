/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge;

import ch.nexpose.sge.objects.Object2D;
import java.util.ArrayList;

/**
 *
 * @author cansik
 */
public class CollisionDetector {
    
    public static ArrayList<Collision> detectCollisions(ArrayList<Object2D> objects)
    {
        ArrayList<Collision> collisions = new ArrayList<Collision>();
        
        for(int c = 0; c < objects.size(); c++)
        {
            for(int i = c + 1; i < objects.size(); i++)            
            {
               Collision col = objects.get(c).detectCollision(objects.get(i));
               if(col != null)
                   collisions.add(col);
            }
        }
        
        return collisions;
    }
    
}
