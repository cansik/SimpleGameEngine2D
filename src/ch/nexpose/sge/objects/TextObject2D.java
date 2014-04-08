package ch.nexpose.sge.objects;

import java.awt.*;

/**
 * Created by cansik on 08/04/14.
 */
public class TextObject2D extends MovingObject2D
{
    final String DEFAULT_FONT = "Verdana";

    String text;
    Font font;

    public TextObject2D(String text)
    {
        this.text = text;
        this.font = new Font(DEFAULT_FONT, Font.PLAIN, 20);
    }

    public Font getFont()
    {
        return font;
    }

    public void setFont(Font font)
    {
        this.font = font;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    @Override
    public Dimension getSize()
    {
        Canvas c = new Canvas();
        FontMetrics fm = c.getFontMetrics(font);

        int width = fm.stringWidth(this.text);

        return new Dimension(width, fm.getHeight());
    }

    @Override
    public void paint(Graphics2D g)
    {
        g.setColor(this.getColor());
        g.setFont(this.font);
        g.drawString(this.text, this.getLocation().x, this.getLocation().y);
    }

}
