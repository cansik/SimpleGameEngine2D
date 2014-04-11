package ch.nexpose.sgetest.space;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.GameStory;
import ch.nexpose.sge.StoryBoard;
import ch.nexpose.sge.objects.StaticObject2D;
import ch.nexpose.sge.ui.GameScene;

import java.awt.*;
import java.util.Random;

/**
 * Created by cansik on 08/04/14.
 */
public class SpaceGameStory implements GameStory
{
    StoryBoard storyBoard;
    SimpleGameEngine2D engine;
    Starship ship;

    public SpaceGameStory(SimpleGameEngine2D engine, StoryBoard storyBoard)
    {
        this.engine = new SimpleGameEngine2D(engine.getScene());
        this.storyBoard = storyBoard;
    }

    private void initStory()
    {
        ship = new Starship(engine);
        ship.setBordercheck(true);
        ship.setLocation(new Point(0,
                (int)(this.engine.getScene().getHeight() / 2) - (int)(ship.getSize().height / 2)));

        engine.addGameObject(ship);

        /*//Key listener
        engine.getScene().addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                ship.simpleSteering(evt.getKeyCode());
            }
        });*/
        this.engine.addGameStory(this);
    }

    @Override
    public void runStory()
    {
        initStory();
        engine.startEngine();
    }

    @Override
    public void nextFrame()
    {
        if(randInt(0, 20) == 1)
        {
            EnemyStarShip enemy = new EnemyStarShip(engine);
            enemy.setLocation(new Point(engine.getScene().getWidth(), randInt(0, engine.getScene().getHeight())));

            engine.addGameObject(enemy);
        }
    }

    public int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
