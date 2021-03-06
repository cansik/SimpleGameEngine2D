/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge.objects;

import java.awt.event.KeyEvent;
import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;

/**
 *
 * @author cansik
 * @deprecated use a {@link ch.nexpose.sge.story.IGameStory} to implement player interaction instead.
 */
public class PlayerObject2D extends MovingObject2D {

    public PlayerObject2D(SimpleGameEngine2D engine)
    {
        super(engine);
    }

    public void simpleSteering()
    {
        this.setDirection(Direction.NONE);

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
