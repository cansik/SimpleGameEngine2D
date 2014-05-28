package ch.nexpose.sge.objects;

import ch.nexpose.sge.SimpleGameEngine2D;
import com.sun.prism.*;

import java.awt.*;
import java.awt.font.FontRenderContext;

/**
 * Created by cansik on 08/04/14.
 */
public class TextObject2D extends MovingObject2D
{
    final String DEFAULT_FONT = "Verdana";

    String text;
    Font font;

    public TextObject2D(SimpleGameEngine2D engine)
    {
        this(engine, "");
    }

    public TextObject2D(SimpleGameEngine2D engine, String text)
    {
        super(engine);
        this.text = text;
        this.font = new Font(DEFAULT_FONT, Font.PLAIN, 20);

        this.setLocation(new Point(0, 0));
        this.setCollisionable(false);
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
        FontMetrics fm = engine.getScene().getFontMetrics(font);
        int width = fm.stringWidth(this.text);
        return new Dimension(width, Math.round(getTextHeight(this.text)));
    }

    /**
     * Returns the height of the text depending on the scene metrics.
     * @param label
     * @return
     */
    private float getTextHeight(String label) {
        Graphics2D g2 = (Graphics2D)engine.getScene().getGraphics();
        FontRenderContext frc = g2.getFontRenderContext();
        return this.font.getLineMetrics(label, frc).getAscent() - this.font.getLineMetrics(label, frc).getDescent();
    }

    @Override
    public void setLocation(Point location)
    {
        super.setLocation(new Point(location.x, location.y));
    }

    @Override
    public void paint(Graphics2D g)
    {
        g.setColor(this.getColor());
        g.setFont(this.font);

        //fixes bug that a drawn text has 0,0 not left-top -> STILL NOT EXACTLY
        g.drawString(this.text, this.getLocation().x,
                this.getLocation().y + getSize().height);
    }

}
