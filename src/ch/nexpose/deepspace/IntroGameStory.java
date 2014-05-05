package ch.nexpose.deepspace;

import ch.nexpose.sge.*;
import ch.nexpose.sge.objects.FlashingText;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 05/05/14.
 */
public class IntroGameStory implements IGameStory
{
    private SimpleGameEngine2D _engine;
    private StoryBoard _storyBoard;

    public IntroGameStory(SimpleGameEngine2D engine, StoryBoard storyBoard)
    {
        _engine = new SimpleGameEngine2D(engine.getScene());
        _storyBoard = storyBoard;

        _engine.addGameStory(this);
    }

    @Override
    public void nextFrame()
    {
        //on key hit -> next
        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_SPACE) ||
                _engine.getInputTracker().isKeyPressed(KeyEvent.VK_ENTER))
        {
            _engine.stopEngine();
            _storyBoard.getNextStory().runStory();
        }
    }

    @Override
    public void runStory()
    {
        //INTRO TEXT
        FlashingText introText = new FlashingText(_engine, "Deep Space");
        introText.setColor(Color.blue);
        introText.setFlashSpeed(5);
        introText.setFont(new Font("Verdana", Font.PLAIN, 50));
        introText.center();


        //add game objects
        _engine.addGameObject(introText);
        _engine.startEngine();
    }
}
