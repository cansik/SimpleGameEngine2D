/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sgetest;

import java.awt.*;
import java.awt.event.KeyEvent;
import ch.nexpose.sge.objects.PlayerObject2D;

/**
 *
 * @author cansik
 */
public class Starship extends PlayerObject2D {
    Image rocket;


    public Starship()
    {
        this.setColor(Color.MAGENTA);
        this.setSize(new Dimension(30, 30));
        this.setSpeed(4);
        rocket = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/rocket.png"));
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

    @Override
    public void paint(Graphics2D g)
    {
        //g.setColor(this.getColor());
        //g.fillOval(this.getLocation().x, this.getLocation().y, this.getSize().width, this.getSize().height);
        g.drawImage(rocket, this.getLocation().x, this.getLocation().y, null);
    }
}
