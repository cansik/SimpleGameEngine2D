package ch.nexpose.sge.collisions;

/**
 * Created by cansik on 08/04/14.
 */
public interface CollisionListener
{
    /**
     * Event which will be fired if a collision on the implemented object happened.
     * @param c
     */
    public void collisionDetected(Collision c);
}
