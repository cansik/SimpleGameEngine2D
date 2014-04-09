package ch.nexpose.sgetest.space;

import ch.nexpose.sge.objects.TextObject2D;

import java.awt.*;

/**
 * Created by cansik on 09/04/14.
 */
public class FlashingText extends TextObject2D
{
    int flashSpeed = 10;
    int currentAlpha = 255;


    public FlashingText(String text)
    {
        super(text);
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
