package ch.nexpose.sge;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cansik on 12/04/14.
 */
public class Animation
{
    int frameIndex = 0;
    int speedIndex = 0;
    int speed = 1;
    ArrayList<Image> frames;

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

    public Animation()
    {
        frames = new ArrayList<Image>();
    }

    public void play()
    {
        this.frameIndex = 0;
        this.speedIndex = 0;
    }

    public Image next()
    {
        if(++speedIndex > speed)
        {
            speedIndex = 0;

            if (frameIndex + 1 < frames.size())
                return frames.get(frameIndex++);
            else
                return null;
        }
        else
        {
            return frames.get(frameIndex);
        }
    }
}
