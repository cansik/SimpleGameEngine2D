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

    public void simpleSteering(int keyCode)
    {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                this.setDirection(Direction.LEFT);
                break;

            case KeyEvent.VK_RIGHT:
                this.setDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_UP:
                this.setDirection(Direction.UP);
                break;

            case KeyEvent.VK_DOWN:
                this.setDirection(Direction.DOWN);
                break;
        }
    }
}
