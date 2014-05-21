package ch.nexpose.deepspace;

import ch.nexpose.deepspace.gui.DialogStory;
import ch.nexpose.deepspace.gui.InfoText;
import ch.nexpose.deepspace.gui.ScoreText;
import ch.nexpose.deepspace.gui.ScoreType;
import ch.nexpose.deepspace.objects.*;
import ch.nexpose.sge.Direction;
import ch.nexpose.sge.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.StoryBoard;
import ch.nexpose.sge.ui.GameScene;
import ch.nexpose.sgetest.space.RandomGenerator;

import java.awt.*;
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
    ScoreText scoreText;

    int _enemySpeed;
    boolean _enemyShoot;
    int _scoreGoal;
    int _levelNumber;

    boolean shotKeyWasPressed;

    public LevelGameStory(GameScene scene, StoryBoard storyBoard, int levelNumber, int enemySpeed, boolean enemyShoot, int scoreGoal)
    {
        _engine = new SimpleGameEngine2D(scene);
        _storyBoard = storyBoard;

        _scoreGoal = scoreGoal;
        _enemyShoot = enemyShoot;
        _enemySpeed = enemySpeed;
        _levelNumber = levelNumber;

        _engine.addGameStory(this);
    }

    @Override
    public void nextFrame()
    {
        playerSteering();
        objectCreation();

        //show in game Menu (pause)
        if(_engine.getInputTracker().isKeyPressed(KeyEvent.VK_ESCAPE))
        {
            MenuGameStory realMenu = new MenuGameStory(_engine.getScene());
            realMenu.runStory();

            DialogStory dialog = new DialogStory(_engine.getScene(), "Quit game?", realMenu, this);
            dialog.setBackgroundImage(_engine.getScene().getBackgroundImage());
            dialog.runStory();
        }
    }

    @Override
    public void runStory()
    {
        //Initialize Objects
        player = new SpaceShip(_engine);

        //score text
        scoreText = new ScoreText(_engine);
        scoreText.setLifes(3);
        scoreText.setLevel(_levelNumber);

        //info text
        InfoText levelInfo = new InfoText(_engine, "Level " + _levelNumber, 48 * 2);
        levelInfo.setColor(new Color(0, 153, 255));
        levelInfo.setFont(new Font("Verdana", Font.PLAIN, 50));
        levelInfo.setOpacity(230);
        levelInfo.centerOnScene();

        //Background
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/background.png"));
        MovingBackground background = new MovingBackground(_engine, bg, new Dimension(1000, 480));

        //add objects
        _engine.addGameObject(background);
        _engine.addGameObject(scoreText);
        _engine.addGameObject(player);
        _engine.addGameObject(levelInfo);
        _engine.startEngine();
    }

    private void showHitInfo()
    {
        InfoText levelInfo = new InfoText(_engine, "You lost a life!", 20);
        levelInfo.setColor(new Color(255, 17, 0));
        levelInfo.setFont(new Font("Verdana", Font.PLAIN, 50));
        levelInfo.setOpacity(230);
        levelInfo.centerOnScene();

        _engine.addGameObject(levelInfo);
    }

    public void scorePoint(ScoreType score)
    {
        switch(score)
        {
            case Kill:
                scoreText.setPoints(scoreText.getPoints() + 1);

                if(scoreText.getPoints() == _scoreGoal)
                {
                    //win
                    showWinLoseScreen(true);
                }
                break;

            case Life:
                scoreText.setLifes(scoreText.getLifes() - 1);
                showHitInfo();

                if(scoreText.getLifes() < 1)
                {
                    //lose
                    showWinLoseScreen(false);
                }
                break;
        }
    }

    private void showWinLoseScreen(boolean won)
    {
        _engine.stopEngine();

        IGameStory nextStory;
        String infoMessage;

        if(won)
        {
            infoMessage = "You have won in level " + _levelNumber + "!";
            nextStory = _storyBoard.getNextStory();
            nextStory.runStory();
        }
        else
        {
            MenuGameStory realMenu = new MenuGameStory(_engine.getScene());
            realMenu.runStory();

            nextStory = realMenu;
            infoMessage = "You have lost in level " + _levelNumber + "!";
        }

        DialogStory dialog = new DialogStory(_engine.getScene(), infoMessage, nextStory);
        dialog.setBackgroundImage(_engine.getScene().getBackgroundImage());
        dialog.runStory();
    }

    private void objectCreation()
    {
        //enemy space ship
        if(RandomGenerator.randInt(0, 30) == 1)
        {
            EnemySpaceShip enemy = new EnemySpaceShip(_engine, _enemyShoot);
            enemy.setLocation(new Point((int)_engine.getScene().getViewPortSize().getWidth(),
                    RandomGenerator.randInt(enemy.getSize().height,
                            (int)_engine.getScene().getViewPortSize().getHeight() - enemy.getSize().height)));

            enemy.push(_enemySpeed, Direction.LEFT);
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
