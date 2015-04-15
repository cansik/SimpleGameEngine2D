package ch.nexpose.sge.controls;

import ch.nexpose.sge.controls.leap.LeapMotionTracker;
import ch.nexpose.sge.ui.GameScene;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by cansik on 10/04/14.
 */
public class InputTracker
{
    HashSet<Integer> keyBoardEvents;
    GameScene scene;
    KeyAdapter keyAdapter;
    boolean detectionPaused;
    LeapMotionTracker leapTracker;

    public InputTracker(GameScene scene)
    {
        this.scene = scene;
        leapTracker = new LeapMotionTracker();
    }

    public boolean isDetectionPaused()
    {
        return detectionPaused;
    }

    public void setDetectionPaused(boolean detectionPaused)
    {
        this.detectionPaused = detectionPaused;
    }

    public LeapMotionTracker getLeapTracker() {
        return leapTracker;
    }

    /**
     * Opens the input manager to listen on events.
     */
    public void openInputManager()
    {
        this.keyBoardEvents = new HashSet<Integer>();

        this.keyAdapter = (new java.awt.event.KeyAdapter()
        {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                if(!isDetectionPaused())
                    keyBoardEvents.add(evt.getKeyCode());
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                keyBoardEvents.remove(evt.getKeyCode());
            }
        });

        scene.addKeyListener(keyAdapter);
        leapTracker.startTracker();
    }

    /**
     * Close the input manager.
     */
    public void closeInputManager()
    {
        scene.removeKeyListener(keyAdapter);
        leapTracker.stopTracker();
    }

    /**
     * Check if key is currently pressed.
     * @param keyCode
     * @return
     */
    public boolean isKeyPressed(int keyCode)
    {
        return keyBoardEvents.contains(keyCode);
    }

    /**
     * Releases a key even if it is currently pressed.
     * @param keyCode
     */
    public void releaseKey(int keyCode)
    {
        if(isKeyPressed(keyCode))
            keyBoardEvents.remove(keyCode);
    }
}
