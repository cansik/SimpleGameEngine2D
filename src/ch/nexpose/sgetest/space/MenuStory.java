package ch.nexpose.sgetest.space;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.GameStory;
import ch.nexpose.sge.StoryBoard;
import ch.nexpose.sge.objects.TextObject2D;

import java.awt.*;

/**
 * Created by cansik on 08/04/14.
 */
public class MenuStory implements GameStory
{
    StoryBoard storyBoard;
    SimpleGameEngine2D engine;
    boolean keyPressed = false;

    public MenuStory(SimpleGameEngine2D engine, StoryBoard storyBoard)
    {
        this.engine = new SimpleGameEngine2D(engine.getScene());
        this.storyBoard = storyBoard;
    }

    private void initMenu()
    {
        //Key listener
        engine.getScene().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keyPressed = true;
            }
        });

        TextObject2D startText = new TextObject2D("press any key to start!");
        startText.setColor(Color.green);
        startText.setLocation(new Point((this.engine.getScene().getWidth() / 2) - (int)(startText.getSize().width / 2),
                (int)(this.engine.getScene().getHeight() / 2) - (int)(startText.getSize().height / 2)));

        engine.addGameObject(startText);
        engine.addGameStory(this);
    }

    @Override
    public void runStory()
    {
        initMenu();
        engine.startEngine();
    }

    @Override
    public void nextFrame()
    {
        if(keyPressed)
        {
            engine.stopEngine();
            engine.getScene().removeAll();
            storyBoard.getNextStory().runStory();
        }
    }
}