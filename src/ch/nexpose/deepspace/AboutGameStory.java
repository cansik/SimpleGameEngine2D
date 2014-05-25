package ch.nexpose.deepspace;

import ch.nexpose.sge.story.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.deepspace.gui.FlashingText;
import ch.nexpose.sge.ui.GameScene;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 21/05/14.
 */
public class AboutGameStory implements IGameStory
{
    private SimpleGameEngine2D _engine;

    public AboutGameStory(GameScene scene)
    {
        _engine = new SimpleGameEngine2D(scene);
        _engine.addGameStory(this);
    }

    @Override
    public void nextFrame()
    {
        //on key hit -> go back
        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_SPACE) ||
                _engine.getInputTracker().isKeyPressed(KeyEvent.VK_ENTER))
        {
            _engine.stopEngine();
            new MenuGameStory(_engine.getScene()).runStory();
        }


    }

    @Override
    public void runStory()
    {
        //INTRO TEXT
        FlashingText introText = new FlashingText(_engine, "Developed by Florian Bruggisser");
        introText.setColor(new Color(147, 255, 133));
        introText.setFlashSpeed(5);
        introText.setFont(new Font("Verdana", Font.PLAIN, 30));
        introText.centerOnScene();


        //add game objects
        _engine.addGameObject(introText);
        _engine.startEngine();
    }

    @Override
    public void resumeStory()
    {
        _engine.startEngine();
    }

    @Override
    public void stopStory()
    {
        _engine.stopEngine();
    }
}
