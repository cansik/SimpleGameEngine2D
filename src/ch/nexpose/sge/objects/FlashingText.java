package ch.nexpose.sge.objects;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.TextObject2D;

import java.awt.*;

/**
 * Created by cansik on 09/04/14.
 */
public class FlashingText extends TextObject2D
{
    int flashSpeed = 10;
    int currentAlpha = 255;

    public int getFlashSpeed()
    {
        return flashSpeed;
    }

    public void setFlashSpeed(int flashSpeed)
    {
        this.flashSpeed = flashSpeed;
    }

    public FlashingText(SimpleGameEngine2D engine, String text)
    {
        super(engine, text);
    }

    @Override
    public void paint(Graphics2D g)
    {
        this.setColor(new Color(this.getColor().getRed(),
                this.getColor().getGreen(),
                this.getColor().getBlue(),
                currentAlpha));

        super.paint(g);

        if(currentAlpha + flashSpeed < 0 || currentAlpha + flashSpeed > 255)
        {
            flashSpeed = flashSpeed * (-1);
        }

            currentAlpha += flashSpeed;
    }

}
