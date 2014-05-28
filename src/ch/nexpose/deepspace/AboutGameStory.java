package ch.nexpose.deepspace;

import ch.nexpose.deepspace.objects.MovingBackground;
import ch.nexpose.deepspace.screen.RandomGenerator;
import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.fx.dolly.Dolly;
import ch.nexpose.sge.fx.dolly.DollyProperty;
import ch.nexpose.sge.objects.BaseObject2D;
import ch.nexpose.sge.story.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.deepspace.gui.FlashingText;
import ch.nexpose.sge.ui.GameScene;
import ch.nexpose.deepspace.objects.GravityBall;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 21/05/14.
 */
public class AboutGameStory implements IGameStory
{
    private SimpleGameEngine2D _engine;
    private FlashingText introText;

    public AboutGameStory(GameScene scene)
    {
        _engine = new SimpleGameEngine2D(scene);
        _engine.addNextFrameListener(this);
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

        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_DOWN))
            pushAllBalls(1, Direction.DOWN);

        //create new balls
        if(RandomGenerator.getBooleanByChance(10))
            _engine.addGameObject(new GravityBall(_engine, Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ballblue.png"))));

        System.out.println(introText.getLocation().toString());
    }

    public void pushAllBalls(double impact, Direction direction)
    {
        for(BaseObject2D obj : _engine.getGameObjects())
            if(obj instanceof GravityBall)
                ((GravityBall)obj).push(impact, direction);
    }

    @Override
    public void runStory()
    {
        //INTRO TEXT
        introText = new FlashingText(_engine, "Developed by Florian Bruggisser");
        introText.setColor(new Color(147, 255, 133));
        introText.setFlashSpeed(5);
        introText.setFont(new Font("Verdana", Font.PLAIN, 30));
        introText.centerOnScene();

        Dolly dolly = new Dolly(introText);
        dolly.addProperty(new DollyProperty("x", "getLocation", 1f, 50, 100));
        dolly.addProperty(new DollyProperty("y", "getLocation", 1f, 50, 100));
        dolly.setMoving(true);

        //Background
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/background_water.png"));
        MovingBackground background = new MovingBackground(_engine, bg);
        _engine.addGameObject(background);

        for(int i = 0; i < 10; i++)
        {
            _engine.addGameObject(new GravityBall(_engine, Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ballblue.png"))));
        }

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
