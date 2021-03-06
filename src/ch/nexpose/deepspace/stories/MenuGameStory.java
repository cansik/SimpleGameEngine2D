package ch.nexpose.deepspace.stories;

import ch.nexpose.deepspace.gui.MenuControl;
import ch.nexpose.deepspace.gui.MenuItem;
import ch.nexpose.deepspace.gui.MovingBackground;
import ch.nexpose.deepspace.screen.LevelParser;
import ch.nexpose.sge.fx.audio.SoundPlayer;
import ch.nexpose.sge.story.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.story.StoryBoard;
import ch.nexpose.sge.ui.GameScene;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 05/05/14.
 */
public class MenuGameStory implements IGameStory
{
    SimpleGameEngine2D _engine;
    MenuControl _menu;

    public MenuGameStory(GameScene scene)
    {
        _engine = new SimpleGameEngine2D(scene);
        _engine.addNextFrameListener(this);
    }

    @Override
    public void nextFrame()
    {
        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_ENTER))
        {
            if(_menu.getSelectedItem().getTag() == null)
                System.exit(0);

            if(_menu.getSelectedItem().getText().startsWith("Mute"))
            {
                SoundPlayer.isMute = !SoundPlayer.isMute;
                if(SoundPlayer.isMute)
                    SoundPlayer.BackgroundMusic.interrupt();
                else
                    SoundPlayer.BackgroundMusic.start();

                _menu.getSelectedItem().setText("Mute " + SoundPlayer.isMute);
            }
            else
            {
                _engine.stopEngine();
                IGameStory story = (IGameStory)_menu.getSelectedItem().getTag();
                story.runStory();
            }
        }

        //easteregg
        if (_engine.getInputTracker().isKeyPressed(KeyEvent.VK_S) &&
                _engine.getInputTracker().isKeyPressed(KeyEvent.VK_R) &&
                _engine.getInputTracker().isKeyPressed(KeyEvent.VK_A))
        {
            _engine.stopEngine();

        }
    }

    @Override
    public void runStory()
    {
        _engine.resetEngine();

        //Create LevelGameStoryBoard
        LevelParser parser = new LevelParser();
        StoryBoard levelGameStoryBoard = parser.ReadLevels(_engine.getScene());
        levelGameStoryBoard.addGameStory(new AboutGameStory(_engine.getScene(), true));

        _menu = new MenuControl(_engine);
        _menu.addItem(new MenuItem(_engine, "New Game", levelGameStoryBoard.getNextStory()));
        _menu.addItem(new MenuItem(_engine, "About", new AboutGameStory(_engine.getScene(), false)));
        //_menu.addItem(new MenuItem(_engine, "Mute true", "bla"));
        _menu.addItem(new MenuItem(_engine, "Exit"));

        _menu.setColor(Color.white);
        _menu.setTextSize(35);
        _menu.centerOnScene();

        //Background
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/background.png"));
        MovingBackground background = new MovingBackground(_engine, bg);

        //add game objects
        _engine.addGameObject(background);
        _engine.addGameObject(_menu);
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
