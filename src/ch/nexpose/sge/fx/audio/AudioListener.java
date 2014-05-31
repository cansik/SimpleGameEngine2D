package ch.nexpose.sge.fx.audio;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Created by cansik on 31/05/14.
 */
public class AudioListener implements LineListener
{
    private boolean done = false;

    @Override
    public synchronized void update(LineEvent event)
    {
        LineEvent.Type eventType = event.getType();
        if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE)
        {
            done = true;
            notifyAll();
        }
    }

    public synchronized void stop()
    {
        done = true;
        notifyAll();
    }

    public synchronized void waitUntilDone() throws InterruptedException
    {
        while (!done)
        {
            wait();
        }
    }
}
