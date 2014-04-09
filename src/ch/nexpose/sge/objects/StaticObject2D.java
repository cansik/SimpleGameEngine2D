/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge.objects;

import ch.nexpose.sge.SimpleGameEngine2D;

import java.awt.*;

/**
 *
 * @author cansik
 */
public class StaticObject2D extends Object2D {
    int livecout = 0;

    public StaticObject2D(SimpleGameEngine2D engine)
    {
        super(engine);
    }

    @Override
    public void paint(Graphics2D g)
    {
        super.paint(g);

        if(livecout > 120)
        {
            this.setAlive(false);
        }

        livecout++;
    }
}
