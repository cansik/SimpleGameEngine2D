package ch.nexpose.deepspace;

import ch.nexpose.deepspace.gui.DialogStory;
import ch.nexpose.deepspace.gui.InfoText;
import ch.nexpose.deepspace.gui.ScoreText;
import ch.nexpose.deepspace.gui.ScoreType;
import ch.nexpose.deepspace.objects.*;
import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.story.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.story.StoryBoard;
import ch.nexpose.sge.ui.GameScene;
import ch.nexpose.deepspace.screen.RandomGenerator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by cansik on 05/05/14.
 */
public class LevelGameStory implements IGameStory
{
    final double PLAYER_SPEED = 1;
    final double ENEMY_SPEED = 2.5;

    SimpleGameEngine2D _engine;
    StoryBoard _storyBoard;
    SpaceShip player;
    ScoreText scoreText;

    int _levelNumber;

    int scoreGoal;
    int enemySpeedingChance;
    int enemyShootingChance;
    int enemySpawnChance;

    boolean shotKeyWasPressed;

    public LevelGameStory(GameScene scene, StoryBoard storyBoard, int levelNumber)
    {
        _engine = new SimpleGameEngine2D(scene);
        _storyBoard = storyBoard;

        _levelNumber = levelNumber;
        _engine.addNextFrameListener(this);
    }

    public int getEnemySpeedingChance()
    {
        return enemySpeedingChance;
    }

    public void setEnemySpeedingChance(int enemySpeedingChance)
    {
        this.enemySpeedingChance = enemySpeedingChance;
    }

    public int getEnemyShootingChance()
    {
        return enemyShootingChance;
    }

    public void setEnemyShootingChance(int enemyShootingChance)
    {
        this.enemyShootingChance = enemyShootingChance;
    }

    public int getEnemySpawnChance()
    {
        return enemySpawnChance;
    }

    public void setEnemySpawnChance(int enemySpawnChance)
    {
        this.enemySpawnChance = enemySpawnChance;
    }

    public int getScoreGoal()
    {
        return scoreGoal;
    }

    public void setScoreGoal(int scoreGoal)
    {
        this.scoreGoal = scoreGoal;
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
        scoreText.setLifes(5);
        scoreText.setMaxlifes(10);
        scoreText.setLevel(_levelNumber);

        //info text
        InfoText levelInfo = new InfoText(_engine, "Level " + _levelNumber, 48 * 2);
        levelInfo.setColor(new Color(0, 153, 255));
        levelInfo.setFont(new Font("Verdana", Font.PLAIN, 50));
        levelInfo.setOpacity(230);
        levelInfo.centerOnScene();

        //Background
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/background.png"));
        MovingBackground background = new MovingBackground(_engine, bg);

        //add objects
        _engine.addGameObject(background);
        _engine.addGameObject(scoreText);
        _engine.addGameObject(player);
        _engine.addGameObject(levelInfo);
        _engine.startEngine();
    }

    /**
     * Shows that the player was hit in a hud.
     */
    private void showHitInfo()
    {
        InfoText levelInfo = new InfoText(_engine, "You lost a life!", 20);
        levelInfo.setColor(new Color(255, 17, 0));
        levelInfo.setFont(new Font("Verdana", Font.PLAIN, 50));
        levelInfo.setOpacity(230);
        levelInfo.centerOnScene();

        _engine.addGameObject(levelInfo);
    }

    /**
     * Fire if a new point has been scored.
     * @param score
     */
    public void scorePoint(ScoreType score)
    {
        switch(score)
        {
            case Kill:
                scoreText.setPoints(scoreText.getPoints() + 1);

                if(scoreText.getPoints() == scoreGoal)
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

    /**
     * Shows win or lose screen.
     * @param won
     */
    private void showWinLoseScreen(boolean won)
    {
        _engine.stopEngine();

        IGameStory nextStory;
        String infoMessage;
        BufferedImage currentFrame = _engine.getScene().getBackgroundImage();

        if(won)
        {
            infoMessage = "You have reached level " + (_levelNumber + 1) + "!";
            nextStory = _storyBoard.getNextStory();
            nextStory.runStory();
        }
        else
        {
            MenuGameStory realMenu = new MenuGameStory(_engine.getScene());
            realMenu.runStory();

            nextStory = realMenu;
            infoMessage = "You have lost level " + _levelNumber + "!";
        }

        DialogStory dialog = new DialogStory(_engine.getScene(), infoMessage, nextStory);
        dialog.setBackgroundImage(currentFrame);
        dialog.runStory();
    }

    /**
     * Creates spawn obejcts like enemy spaceships.
     */
    private void objectCreation()
    {
        //enemy space ship
        if(RandomGenerator.getBooleanByChance(enemySpawnChance))
        {
            EnemySpaceShip enemy = new EnemySpaceShip(_engine, ENEMY_SPEED, RandomGenerator.getBooleanByChance(enemyShootingChance),
                    RandomGenerator.getBooleanByChance(enemySpeedingChance));

            enemy.setLocation(new Point((int)_engine.getScene().getViewPortSize().getWidth(),
                    RandomGenerator.randInt(enemy.getSize().height,
                            (int)_engine.getScene().getViewPortSize().getHeight() - enemy.getSize().height)));

            _engine.addGameObject(enemy);
        }
    }

    /**
     * Handles player interaction.
     */
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
