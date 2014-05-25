package ch.nexpose.sge.objects;

import ch.nexpose.sge.fx.Animation;
import ch.nexpose.sge.SimpleGameEngine2D;

import javax.swing.*;

import java.awt.*;

/**
 * Created by cansik on 12/04/14.
 */
public class AnimatedObject2D extends TexturedObject2D
{
    Animation animation;
    boolean animated = false;

    public Animation getAnimation()
    {
        return animation;
    }

    public void setAnimation(Animation animation)
    {
        this.animation = animation;
    }

    public boolean isAnimated()
    {
        return animated;
    }

    public AnimatedObject2D(SimpleGameEngine2D engine, Image texture)
    {
        this(engine, texture, new Animation());
    }

    public AnimatedObject2D(SimpleGameEngine2D engine, Image texture, Animation animation)
    {
        super(engine, texture);
        this.animation = animation;
    }

    /**
     * Plays the animation.
     */
    public void playAnimation()
    {
        if(!animated)
        {
            animation.play();
            animated = true;
        }
    }

    @Override
    public void paint(Graphics2D g)
    {
        if(animated)
        {
            Image frame = animation.next();
            if(frame != null)
            {
                ImageIcon ico = new ImageIcon(frame);
                Dimension frameSize = new Dimension(ico.getIconWidth(), ico.getIconHeight());

                //draw original texture
                if(animation.isOverlay())
                    g.drawImage(texture, this.getLocation().x, this.getLocation().y, frameSize.width, frameSize.height, null);

                //draw animation
                g.drawImage(frame, this.getLocation().x, this.getLocation().y, frameSize.width, frameSize.height, null);

                //g.drawImage(frame, this.getLocation().x, this.getLocation().y, this.getSize().width, this.getSize().height, null);
            }
            else
            {
                if(animation.isLooping())
                {
                    animation.play();
                    super.paint(g);
                }
                else
                {
                    animated = false;
                    super.paint(g);
                }
            }

        }
        else
        {
            super.paint(g);
        }
    }
}
