/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sgetest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import sge.engine.objects.PlayerObject2D;

/**
 *
 * @author cansik
 */
public class Starship extends PlayerObject2D {
    
    public Starship()
    {
        this.setColor(Color.MAGENTA);
        this.setSize(new Dimension(30, 30));
        this.setSpeed(4);
    }
    
    @Override
    public void simpleSteering(int keyCode)
    {
        super.simpleSteering(keyCode);
        
        if(keyCode == KeyEvent.VK_SPACE)
        {
            Bullet b = new Bullet(this);
            b.setEngine(this.getEngine());
            this.getEngine().getGameObjects().add(b);
        }
    }
}
