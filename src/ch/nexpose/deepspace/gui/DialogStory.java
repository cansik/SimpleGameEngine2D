package ch.nexpose.deepspace.gui;

import ch.nexpose.sge.story.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.TextObject2D;
import ch.nexpose.sge.objects.TexturedObject2D;
import ch.nexpose.sge.ui.GameScene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Dialog hud over the screen.
 * Created by cansik on 21/05/14.
 */
public class DialogStory implements IGameStory
{
    private SimpleGameEngine2D _engine;

    MenuControl _menu;

    String _message;
    IGameStory _nextStoryYes;
    IGameStory _nextStoryNo;

    BufferedImage backgroundImage;

    public BufferedImage getBackgroundImage()
    {
        return backgroundImage;
    }

    public void setBackgroundImage(BufferedImage backgroundImage)
    {
        this.backgroundImage = backgroundImage;
    }

    public DialogStory(GameScene scene, String message, IGameStory nextStory)
    {
        this(scene, message, nextStory, null);
    }

    public DialogStory(GameScene scene, String message, IGameStory nextStoryYes, IGameStory nextStoryNo)
    {
        _engine = new SimpleGameEngine2D(scene);
        _engine.addGameStory(this);

        _message = message;
        _nextStoryYes = nextStoryYes;
        _nextStoryNo = nextStoryNo;

        //stop current stories
        _nextStoryYes.stopStory();
        if(_nextStoryNo != null)
            _nextStoryNo.stopStory();
    }

    @Override
    public void nextFrame()
    {
        //on key hit -> go back
        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_ENTER))
        {
            _engine.stopEngine();

            if(_menu.getSelectedItem().getTag() == null)
                System.exit(0);

            IGameStory story = (IGameStory)_menu.getSelectedItem().getTag();
            story.resumeStory();
        }
    }

    @Override
    public void runStory()
    {
        //Menu
        _menu = new MenuControl(_engine);

        if(_nextStoryNo != null)
        {
            //Dialog Style YES NO
            _menu.addItem(new MenuItem(_engine, "Yes", _nextStoryYes));
            _menu.addItem(new MenuItem(_engine, "No", _nextStoryNo));
        }
        else
        {
            //Dialog Style Messagebox
            _menu.addItem(new MenuItem(_engine, "OK", _nextStoryYes));
        }

        _menu.setColor(Color.white);
        _menu.setTextSize(35);
        _menu.centerOnScene();

        //Message Text
        TextObject2D messageText = new TextObject2D(_engine, _message);
        messageText.setFont(new Font("Verdana", Font.PLAIN, 40));
        messageText.centerOnScene();
        messageText.setLocation(new Point(messageText.getLocation().x, messageText.getLocation().y - 45));

        //Background
        TexturedObject2D background = new TexturedObject2D(_engine, null);
        background.setTexture(backgroundImage);
        background.setOpacity(0.5f);

        //add game objects
        _engine.addGameObject(background);
        _engine.addGameObject(_menu);
        _engine.addGameObject(messageText);
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
