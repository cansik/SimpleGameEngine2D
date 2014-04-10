package ch.nexpose.sge.controls;

import ch.nexpose.sge.ui.GameScene;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by cansik on 10/04/14.
 */
public class InputManager
{
    ArrayList<KeyEvent> keyBoardEvents;
    GameScene scene;
    java.awt.event.KeyAdapter keyAdapter;

    public InputManager(GameScene scene)
    {
        this.scene = scene;
        this.keyBoardEvents = new ArrayList<KeyEvent>();
    }

    public void openInputManager()
    {
        this.keyAdapter = (new java.awt.event.KeyAdapter()
        {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                keyBoardEvents.add(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                keyBoardEvents.remove(evt);
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
        for(KeyEvent ke : keyBoardEvents)
        {
            if(ke.getKeyCode() == keyCode)
                return true;
        }

        return false;
    }

}
