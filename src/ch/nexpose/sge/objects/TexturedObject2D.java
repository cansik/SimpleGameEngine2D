package ch.nexpose.sge.objects;

import ch.nexpose.sge.SimpleGameEngine2D;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cansik on 12/04/14.
 */
public class TexturedObject2D extends MovingObject2D
{
    Image texture;
    float opacity = 1f;

    public Image getTexture()
    {
        return texture;
    }

    public float getOpacity()
    {
        return opacity;
    }

    public void setOpacity(float opacity)
    {
        this.opacity = opacity;
    }

    public void setTexture(Image texture)
    {
        this.texture = texture;

        //set size
        ImageIcon ico = new ImageIcon(texture);
        this.setSize(new Dimension(ico.getIconWidth(), ico.getIconHeight()));
    }

    public TexturedObject2D(SimpleGameEngine2D engine)
    {
        this(engine, null);
    }

    public TexturedObject2D(SimpleGameEngine2D engine, Image texture)
    {
        super(engine);

        //Todo: use setTexture to set size of the texture => bug
        this.texture = texture;
    }

    @Override
    public void paint(Graphics2D g)
    {
        //Show rectangle for debugging
        //g.drawRect(this.getLocation().x, this.getLocation().y, this.getSize().width, this.getSize().height);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

        if(texture != null)
            g.drawImage(texture, this.getLocation().x, this.getLocation().y, this.getSize().width, this.getSize().height, null);
        else
            super.paint(g);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
