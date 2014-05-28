package ch.nexpose.sge.fx;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by cansik on 12/04/14.
 */
public class Animation
{
    int frameCounter = 0;
    int speedCounter = 0;
    int speed = 1;
    ArrayList<Image> frames;
    boolean looping = false;
    boolean overlay;

    public boolean isOverlay()
    {
        return overlay;
    }

    public void setOverlay(boolean overlay)
    {
        this.overlay = overlay;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public ArrayList<Image> getFrames()
    {
        return frames;
    }

    public void setFrames(ArrayList<Image> frames)
    {
        this.frames = frames;
    }

    public boolean isLooping()
    {
        return looping;
    }

    public void setLooping(boolean looping)
    {
        this.looping = looping;
    }

    public Animation()
    {
        frames = new ArrayList<Image>();
    }

    /**
     * Plays the animation.
     */
    public void play()
    {
        this.frameCounter = 0;
        this.speedCounter = 0;
    }

    /**
     * Returns the next frame of the animation.
     * @return
     */
    public Image next()
    {
        if(++speedCounter > speed)
        {
            speedCounter = 0;

            if (frameCounter + 1 < frames.size())
                return frames.get(frameCounter++);
            else
                return null;
        }
        else
        {
            return frames.get(frameCounter);
        }
    }
}
