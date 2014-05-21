package ch.nexpose.deepspace;

import ch.nexpose.sge.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.StoryBoard;
import ch.nexpose.sge.objects.FlashingText;
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
        FlashingText introText = new FlashingText(_engine, "Deep Space");
        introText.setColor(new Color(0, 153, 255));
        introText.setFlashSpeed(5);
        introText.setFont(new Font("Verdana", Font.PLAIN, 50));
        introText.center();


        //add game objects
        _engine.addGameObject(introText);
        _engine.startEngine();
    }
}
