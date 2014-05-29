package ch.nexpose.deepspace;

import ch.nexpose.deepspace.objects.MovingBackground;
import ch.nexpose.deepspace.screen.RandomGenerator;
import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.fx.dolly.Dolly;
import ch.nexpose.sge.fx.dolly.DollyProperty;
import ch.nexpose.sge.objects.BaseObject2D;
import ch.nexpose.sge.objects.TextObject2D;
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
    private boolean gameFinish;

    public AboutGameStory(GameScene scene, boolean gameFinish)
    {
        _engine = new SimpleGameEngine2D(scene);
        _engine.addNextFrameListener(this);
        this.gameFinish = gameFinish;
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
        //Background
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/background_water.png"));
        MovingBackground background = new MovingBackground(_engine, bg);
        _engine.addGameObject(background);


        TextObject2D title = new TextObject2D(_engine, "Deep Space");
        if(gameFinish)
            title.setText("You finished Deep Space!");
        title.setColor(new Color(163, 248, 255));
        title.setFont(new Font("Verdana", Font.PLAIN, 50));
        title.centerOnScene();
        title.setLocation(new Point(title.getLocation().x, title.getLocation().y  - 40));

        int time = 24;
        Dolly dolly = new Dolly(new TextObject2D(_engine));
        time = AddTitle("developed by Florian", dolly, time);
        time = AddTitle("music by Pascal", dolly, time);
        time = AddTitle("tested by Roman", dolly, time);
        time = AddTitle("supported by Sara", dolly, time);
        time = AddTitle("FHNW Informatik 2014", dolly, time);
        time = AddTitle("www.yarx.ch", dolly, time);
        dolly.move();

        /*
        //Move In
        dolly.addProperty(new DollyProperty("x", "getLocation", 48 * 2, -500, 120, 48));
        //Slow Movement
        dolly.addProperty(new DollyProperty("x", "getLocation", 48 * 2, 120, 250, 48 * 3));
        //Move Out
        dolly.addProperty(new DollyProperty("x", "getLocation", 48 * 2, 250, 852, 48 * 5));
        */

        for(int i = 0; i < 10; i++)
        {
            _engine.addGameObject(new GravityBall(_engine, Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ballblue.png"))));
        }

        //add game objects
        _engine.addGameObject(title);
        _engine.startEngine();
    }

    private int AddTitle(String message, Dolly dolly, int time)
    {
        //Text
        TextObject2D text = new TextObject2D(_engine, message);
        text.setColor(new Color(163, 248, 255));
        text.setFont(new Font("Verdana", Font.PLAIN, 30));
        text.centerOnScene();
        int xmiddle = text.getLocation().x;
        text.setLocation(new Point(-1000, text.getLocation().y + 40));
        _engine.addGameObject(text);

        dolly.setMountedObject(text);

        //Movement
        //Move In
        dolly.addProperty(new DollyProperty("x", "getLocation", 48 * 2, -500, xmiddle - 60, time));
        //Slow Movement
        dolly.addProperty(new DollyProperty("x", "getLocation", 48 * 2, xmiddle - 60, xmiddle + 60, (time += 48 * 2)));
        //Move Out
        dolly.addProperty(new DollyProperty("x", "getLocation", 48 * 2, xmiddle + 60, 852 + 500, (time += 48 * 2)));

        return time;
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
