package ch.nexpose.deepspace;

import ch.nexpose.deepspace.objects.MovingBackground;
import ch.nexpose.sge.*;
import ch.nexpose.deepspace.gui.FlashingText;
import ch.nexpose.sge.story.IGameStory;
import ch.nexpose.sge.ui.GameScene;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 05/05/14.
 */
public class IntroGameStory implements IGameStory
{
    private SimpleGameEngine2D _engine;

    public IntroGameStory(GameScene scene)
    {
        _engine = new SimpleGameEngine2D(scene);

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
        introText.centerOnScene();

        //Background
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/background.png"));
        MovingBackground background = new MovingBackground(_engine, bg, new Dimension(1000, 480));

        //add game objects
        _engine.addGameObject(background);
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
