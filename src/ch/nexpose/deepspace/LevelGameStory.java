package ch.nexpose.deepspace;

import ch.nexpose.deepspace.objects.SpaceShip;
import ch.nexpose.sge.Direction;
import ch.nexpose.sge.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.StoryBoard;

import java.awt.event.KeyEvent;

/**
 * Created by cansik on 05/05/14.
 */
public class LevelGameStory implements IGameStory
{
    final double PLAYER_SPEED = 1;

    SimpleGameEngine2D _engine;
    StoryBoard _storyBoard;
    SpaceShip player;

    public LevelGameStory(SimpleGameEngine2D engine, StoryBoard storyBoard)
    {
        _engine = new SimpleGameEngine2D(engine.getScene());
        _storyBoard = storyBoard;

        _engine.addGameStory(this);
    }

    @Override
    public void nextFrame()
    {
        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_RIGHT))
            player.push(PLAYER_SPEED, Direction.RIGHT);

        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_LEFT))
            player.push(PLAYER_SPEED, Direction.LEFT);

        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_UP))
            player.push(PLAYER_SPEED, Direction.UP);

        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_DOWN))
            player.push(PLAYER_SPEED, Direction.DOWN);

        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_SPACE))
            player.shoot();
    }

    @Override
    public void runStory()
    {
        //Initialize Objects
        player = new SpaceShip(_engine);

        _engine.addGameObject(player);
        _engine.startEngine();
    }
}
