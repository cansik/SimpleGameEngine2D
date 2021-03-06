/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sgetest.space;

import java.awt.*;
import java.awt.event.KeyEvent;

import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.PlayerObject2D;

/**
 *
 * @author cansik
 */
public class Starship extends PlayerObject2D {
    Image rocket;
    Image rocket1;
    Image rocket2;
    boolean imageSwitcher = false;


    public Starship(SimpleGameEngine2D engine)
    {
        super(engine);

        this.setColor(Color.MAGENTA);
        this.setSize(new Dimension(50, 50));
        this.setSpeed(6);
        rocket = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/rocket.png"));
        rocket1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/rocket1.png"));
        rocket2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/rocket2.png"));
    }
    
    @Override
    public void simpleSteering()
    {
        super.simpleSteering();
        
        if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_SPACE))
        {
            Bullet b = new Bullet(this);
            this.getEngine().addGameObject(b);

            //release space
            getEngine().getInputTracker().releaseKey(KeyEvent.VK_SPACE);
        }
    }

    @Override
    public void paint(Graphics2D g)
    {
        if(this.getDirection() == Direction.RIGHT)
        {
            //On boost show animation
            if(imageSwitcher)
                g.drawImage(rocket1, this.getLocation().x, this.getLocation().y, null);
            else
                g.drawImage(rocket2, this.getLocation().x, this.getLocation().y, null);

            imageSwitcher = !imageSwitcher;
        }

        g.drawImage(rocket, this.getLocation().x, this.getLocation().y, null);
    }

    @Override
    public void action()
    {
        super.action();
    }
}
