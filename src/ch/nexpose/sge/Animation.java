package ch.nexpose.sge;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cansik on 12/04/14.
 */
public class Animation
{
    int frameIndex = 0;
    ArrayList<Image> frames;

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
    }

    public Image next()
    {
        if(frameIndex + 1 < frames.size())
            return frames.get(frameIndex++);
        else
            return null;
    }
}
