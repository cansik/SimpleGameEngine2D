package ch.nexpose.deepspace.gui;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.TextObject2D;

import java.awt.*;

/**
 * Created by cansik on 21/05/14.
 */
public class InfoText extends TextObject2D
{
    int hideSpeed = -10;
    int lifeCounter;
    int lifeTime;

    int opacity = 255;

    public int getOpacity()
    {
        return opacity;
    }

    public void setOpacity(int opacity)
    {
        this.opacity = opacity;
    }

    public InfoText(SimpleGameEngine2D engine, String text, int lifeTime)
    {
        super(engine, text);
        this.lifeTime = lifeTime;
        this.setCollisionable(false);
    }

    @Override
    public void action()
    {
        super.action();
        lifeCounter++;
    }

    @Override
    public void paint(Graphics2D g)
    {
        this.setColor(new Color(this.getColor().getRed(),
                this.getColor().getGreen(),
                this.getColor().getBlue(),
                opacity));

        if(lifeCounter > lifeTime)
        {
            opacity += hideSpeed;

            if(opacity <= 0)
            {
                setAlive(false);
                opacity = 0;
            }
        }

        super.paint(g);
    }

}
