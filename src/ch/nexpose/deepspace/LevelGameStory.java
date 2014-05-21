package ch.nexpose.deepspace;

import ch.nexpose.deepspace.gui.ScoreText;
import ch.nexpose.deepspace.objects.EnemySpaceShip;
import ch.nexpose.deepspace.objects.ScoreType;
import ch.nexpose.deepspace.objects.SpaceShip;
import ch.nexpose.sge.Direction;
import ch.nexpose.sge.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.StoryBoard;
import ch.nexpose.sge.ui.GameScene;
import ch.nexpose.sgetest.space.EnemyStarShip;
import ch.nexpose.sgetest.space.RandomGenerator;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 05/05/14.
 */
public class LevelGameStory implements IGameStory
{
    final double PLAYER_SPEED = 1;
    final double ENEMY_SPEED = 2;

    SimpleGameEngine2D _engine;
    StoryBoard _storyBoard;
    SpaceShip player;
    ScoreText scoreText;

    boolean shotKeyWasPressed;

    public LevelGameStory(GameScene scene, StoryBoard storyBoard)
    {
        _engine = new SimpleGameEngine2D(scene);
        _storyBoard = storyBoard;

        _engine.addGameStory(this);
    }

    @Override
    public void nextFrame()
    {
        playerSteering();
        objectCreation();
    }

    @Override
    public void runStory()
    {
        //Initialize Objects
        player = new SpaceShip(_engine);
        scoreText = new ScoreText(_engine);
        scoreText.setLifes(3);

        _engine.addGameObject(scoreText);
        _engine.addGameObject(player);
        _engine.startEngine();
    }

    public void scorePoint(ScoreType score)
    {
        switch(score)
        {
            case Kill:
                scoreText.setPoints(scoreText.getPoints() + 1);
                break;
            case Life:
                scoreText.setLifes(scoreText.getLifes() - 1);

                if(scoreText.getLifes() < 1)
                {
                    _engine.stopEngine();
                    _storyBoard.getNextStory().runStory();
                }

                break;
        }
    }

    private void objectCreation()
    {
        //enemy space ship
        if(RandomGenerator.randInt(0, 30) == 1)
        {
            EnemySpaceShip enemy = new EnemySpaceShip(_engine);
            enemy.setLocation(new Point((int)_engine.getScene().getViewPortSize().getWidth(),
                    RandomGenerator.randInt(enemy.getSize().height,
                            (int)_engine.getScene().getViewPortSize().getHeight() - enemy.getSize().height)));

            enemy.push(ENEMY_SPEED, Direction.LEFT);
            _engine.addGameObject(enemy);
        }
    }

    private void playerSteering()
    {
        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_RIGHT))
            player.push(PLAYER_SPEED, Direction.RIGHT);

        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_LEFT))
            player.push(PLAYER_SPEED, Direction.LEFT);

        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_UP))
            player.push(PLAYER_SPEED, Direction.UP);

        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_DOWN))
            player.push(PLAYER_SPEED, Direction.DOWN);

        if (_engine.getInputTracker().isKeyPressed(KeyEvent.VK_SPACE))
        {
            if (!shotKeyWasPressed)
                player.shoot();
            shotKeyWasPressed = true;
        }
        else
            shotKeyWasPressed = false;
    }
}
