package ch.nexpose.deepspace.gui;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.TextObject2D;

import java.awt.*;

/**
 * Created by cansik on 05/05/14.
 */
public class MenuItem extends TextObject2D
{
    private boolean selected;
    private Object tag;

    public MenuItem(SimpleGameEngine2D engine, String text)
    {
        this(engine, text, null);
    }

    public MenuItem(SimpleGameEngine2D engine, String text, Object tag)
    {
        super(engine, text);
        this.tag = tag;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public Object getTag()
    {
        return tag;
    }

    public void setTag(Object tag)
    {
        this.tag = tag;
    }

    @Override
    public void paint(Graphics2D g)
    {
        g.setColor(getColor());
        if(isSelected())
        {
            g.fillRect(getLocation().x, getLocation().y, getSize().width, getSize().height);

            setColor(negateColor(getColor()));
            super.paint(g);
            setColor(negateColor(getColor()));
        }
        else
        {
            //g.drawRect(getLocation().x, getLocation().y, getSize().width, getSize().height);
            super.paint(g);
        }
    }

    private Color negateColor(Color c)
    {
        return new Color(255 - c.getRed(),
                255 - c.getGreen(),
                255 - c.getBlue());
    }
}
