package ch.nexpose.sge.fx.audio;

import java.io.InputStream;

/**
 * Created by cansik on 21/05/14.
 */
public class SoundPlayer
{
    public static boolean isMute = true;

    public static AudioFile BackgroundMusic;

    /**
     * Creates a new thread to play the given sound file.
     * @param soundFileStream
     */
    public static void playSound(InputStream soundFileStream, boolean loop)
    {
        if(!isMute)
        {
            AudioFile sound = new AudioFile(soundFileStream, loop);

            //First track which will be played is the background music.
            //Todo: implement a better system for sound playing!
            if(BackgroundMusic == null)
                BackgroundMusic = sound;

            sound.start();
        }
    }

    public static void playSound(InputStream soundFileStream)
    {
        playSound(soundFileStream, false);
    }
}
