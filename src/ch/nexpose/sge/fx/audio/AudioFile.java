package ch.nexpose.sge.fx.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cansik on 21/05/14.
 */
public class AudioFile extends Thread
{
    InputStream clipStream;
    boolean loop;
    AudioListener listener;


    public AudioFile(InputStream clipStream, boolean loop)
    {
        super();
        this.clipStream = clipStream;
        this.loop = loop;

    }

    public AudioFile(InputStream clipStream)
    {
        this(clipStream, false);
    }

    /**
     * Thread run function.
     */
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

    /**
     * Plays a sound and waits for the sound to stop.
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws InterruptedException
     */
    private void playSound() throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException
    {

        listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipStream);
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try
            {
                if(loop)
                    clip.loop(Integer.MAX_VALUE);

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

    public void stopSound()
    {
        listener.stop();
    }
}
