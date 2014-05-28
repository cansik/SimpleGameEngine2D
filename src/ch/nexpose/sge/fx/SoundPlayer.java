package ch.nexpose.sge.fx;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.LineEvent.Type;

/**
 * Created by cansik on 21/05/14.
 */
public class SoundPlayer
{
    public static boolean isMute = true;

    /**
     * Creates a new thread to play the given sound file.
     * @param soundFileStream
     */
    public static void playSound(InputStream soundFileStream, boolean loop)
    {
        if(!isMute)
        {
            AudioFile sound = new AudioFile(soundFileStream, loop);
            sound.start();
        }
    }

    public static void playSound(InputStream soundFileStream)
    {
        playSound(soundFileStream, false);
    }
}
