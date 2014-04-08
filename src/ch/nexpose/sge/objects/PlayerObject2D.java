/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge.objects;

import java.awt.event.KeyEvent;
import ch.nexpose.sge.Direction;

/**
 *
 * @author cansik
 */
public abstract class PlayerObject2D extends MovingObject2D {
    
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
