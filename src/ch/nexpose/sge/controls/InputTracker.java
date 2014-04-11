package ch.nexpose.sge.controls;

import ch.nexpose.sge.ui.GameScene;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by cansik on 10/04/14.
 */
public class InputTracker
{
    HashSet<Integer> keyBoardEvents;
    GameScene scene;
    java.awt.event.KeyAdapter keyAdapter;

    public InputTracker(GameScene scene)
    {
        this.scene = scene;
        this.keyBoardEvents = new HashSet<Integer>();
    }

    public void openInputManager()
    {
        this.keyAdapter = (new java.awt.event.KeyAdapter()
        {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                keyBoardEvents.add(evt.getKeyCode());
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                keyBoardEvents.remove(evt.getKeyCode());
            }
        });

        scene.addKeyListener(keyAdapter);
    }

    public void closeInputManager()
    {
        scene.removeKeyListener(keyAdapter);
    }

    public boolean isKeyPressed(int keyCode)
    {
        return keyBoardEvents.contains(keyCode);
    }

    public void releaseKey(int keyCode)
    {
        if(isKeyPressed(keyCode))
            keyBoardEvents.remove(keyCode);
    }

}
