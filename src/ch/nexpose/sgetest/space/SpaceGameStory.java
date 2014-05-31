package ch.nexpose.sgetest.space;

import ch.nexpose.deepspace.gui.MovingBackground;
import ch.nexpose.deepspace.screen.RandomGenerator;
import ch.nexpose.sge.story.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.story.StoryBoard;

import java.awt.*;

/**
 * Created by cansik on 08/04/14.
 */
public class SpaceGameStory implements IGameStory
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
                (int)(this.engine.getScene().getViewPortSize().getHeight() / 2) - (int)(ship.getSize().height / 2)));

        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/background.png"));
        MovingBackground background = new MovingBackground(engine, bg);

        engine.addGameObject(background);
        engine.addGameObject(ship);

        this.engine.addNextFrameListener(this);
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
        if(RandomGenerator.randInt(0, 20) == 1)
        {
            EnemyStarShip enemy = new EnemyStarShip(engine);
            enemy.setLocation(new Point((int)engine.getScene().getViewPortSize().getWidth(), RandomGenerator.randInt(enemy.getSize().height, (int)engine.getScene().getViewPortSize().getHeight() - enemy.getSize().height)));

            engine.addGameObject(enemy);
        }
    }

    @Override
    public void resumeStory()
    {
        engine.startEngine();
    }

    @Override
    public void stopStory()
    {
        engine.stopEngine();
    }
}
