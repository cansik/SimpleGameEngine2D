/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge.objects;

import java.awt.event.KeyEvent;
import ch.nexpose.sge.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;

/**
 *
 * @author cansik
 */
public class PlayerObject2D extends MovingObject2D {

    public PlayerObject2D(SimpleGameEngine2D engine)
    {
        super(engine);
    }

    public void simpleSteering()
    {
        if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_LEFT))
            this.setDirection(Direction.LEFT);
        if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_RIGHT))
            this.setDirection(Direction.RIGHT);
        if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_UP))
            this.setDirection(Direction.UP);
        if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_DOWN))
            this.setDirection(Direction.DOWN);
    }

    @Override
    public void action()
    {
        simpleSteering();
        super.action();
    }
}
