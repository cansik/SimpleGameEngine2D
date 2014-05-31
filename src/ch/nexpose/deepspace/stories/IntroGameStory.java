package ch.nexpose.deepspace.stories;

import ch.nexpose.deepspace.gui.MovingBackground;
import ch.nexpose.sge.*;
import ch.nexpose.sge.fx.audio.SoundPlayer;
import ch.nexpose.sge.fx.dolly.Dolly;
import ch.nexpose.sge.fx.dolly.DollyProperty;
import ch.nexpose.sge.fx.dolly.LoopType;
import ch.nexpose.sge.objects.TextObject2D;
import ch.nexpose.sge.objects.TexturedObject2D;
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

        _engine.addNextFrameListener(this);
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
        TextObject2D title = new TextObject2D(_engine, "Deep Space");
        title.setColor(new Color(163, 248, 255));
        title.setFont(new Font("Verdana", Font.PLAIN, 50));

        TexturedObject2D titleImage = new TexturedObject2D(_engine, title.toImage());
        titleImage.centerOnScene();

        //Animation
        Dolly dolly = new Dolly(titleImage);

        //Grow in
        dolly.addProperty(new DollyProperty("width", "getSize", 48 * 2, 0, titleImage.getSize().width));
        dolly.addProperty(new DollyProperty("height", "getSize", 48 * 2, 0, titleImage.getSize().height));

        //Flashing
        DollyProperty flashing = new DollyProperty("opacity", 48, 1, 0, 48 * 2);
        flashing.setLoopType(LoopType.PingPong);
        //dolly.addProperty(flashing);
        dolly.move();


        //Background
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/background.png"));
        MovingBackground background = new MovingBackground(_engine, bg);

        //BackgroundMusic
        SoundPlayer.isMute = false;
        SoundPlayer.playSound(getClass().getResourceAsStream("/resources/sounds/background_music.wav"), true);

        //add game objects
        _engine.addGameObject(background);
        _engine.addGameObject(titleImage);
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
