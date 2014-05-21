package ch.nexpose.deepspace;

import ch.nexpose.deepspace.gui.MenuControl;
import ch.nexpose.deepspace.gui.MenuItem;
import ch.nexpose.sge.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.StoryBoard;
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
        _engine.addGameStory(this);
    }

    @Override
    public void nextFrame()
    {
        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_ENTER))
        {
            _engine.stopEngine();

            if(_menu.getSelectedItem().getTag() == null)
                System.exit(0);

            IGameStory story = (IGameStory)_menu.getSelectedItem().getTag();
            _engine.stopEngine();
            story.runStory();
        }
    }

    @Override
    public void runStory()
    {
        _engine.resetEngine();

        //Create LevelGameStoryBoard
        StoryBoard levelGameStoryBoard = new StoryBoard();
        levelGameStoryBoard.addGameStory(new LevelGameStory(_engine.getScene(), levelGameStoryBoard));
        levelGameStoryBoard.addGameStory(this);

        _menu = new MenuControl(_engine);
        _menu.addItem(new MenuItem(_engine, "New Game", levelGameStoryBoard.getNextStory()));
        _menu.addItem(new MenuItem(_engine, "About", new AboutGameStory(_engine.getScene())));
        _menu.addItem(new MenuItem(_engine, "Exit"));

        _menu.setColor(Color.white);
        _menu.setTextSize(35);
        _menu.center();

        //add game objects
        _engine.addGameObject(_menu);
        _engine.startEngine();
    }
}
