package ch.nexpose.deepspace;

import ch.nexpose.deepspace.gui.MenuControl;
import ch.nexpose.deepspace.gui.MenuItem;
import ch.nexpose.sge.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.StoryBoard;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 05/05/14.
 */
public class MenuGameStory implements IGameStory
{
    SimpleGameEngine2D _engine;
    StoryBoard _storyBoard;
    MenuControl _menu;

    public MenuGameStory(SimpleGameEngine2D engine, StoryBoard storyBoard)
    {
        _engine = new SimpleGameEngine2D(engine.getScene());
        _storyBoard = storyBoard;

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
            story.runStory();
        }
    }

    @Override
    public void runStory()
    {
        _menu = new MenuControl(_engine);
        _menu.addItem(new MenuItem(_engine, "New Game", new LevelGameStory(_engine, _storyBoard)));
        _menu.addItem(new MenuItem(_engine, "About", new LevelGameStory(_engine, _storyBoard)));
        _menu.addItem(new MenuItem(_engine, "Exit"));

        _menu.setColor(Color.white);
        _menu.setTextSize(35);
        _menu.center();

        //add game objects
        _engine.addGameObject(_menu);
        _engine.startEngine();
    }
}
