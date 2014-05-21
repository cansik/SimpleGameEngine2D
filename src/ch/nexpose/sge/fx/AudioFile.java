package ch.nexpose.sge.fx;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by cansik on 21/05/14.
 */
public class AudioFile extends Thread
{
    File clipFile;

    public AudioFile(File clipFile)
    {
        super();
        this.clipFile = clipFile;
    }

    @Override
    public void run()
    {
        try
        {
            playSound();
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }

    private void playSound() throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException
    {
        class AudioListener implements LineListener
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

            public synchronized void waitUntilDone() throws InterruptedException
            {
                while (!done)
                {
                    wait();
                }
            }
        }
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try
            {
                clip.start();
                listener.waitUntilDone();
            } finally
            {
                clip.close();
            }
        } finally
        {
            audioInputStream.close();
        }

    }
}
