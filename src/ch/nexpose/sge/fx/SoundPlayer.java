package ch.nexpose.sge.fx;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.LineEvent.Type;

/**
 * Created by cansik on 21/05/14.
 */
public class SoundPlayer
{
    public static boolean isMute;

    public static void playSound(URL soundFilePath)
    {
        if(!isMute)
        {
            AudioFile sound = new AudioFile(new File(soundFilePath.getPath()));
            sound.start();
        }
    }
}
