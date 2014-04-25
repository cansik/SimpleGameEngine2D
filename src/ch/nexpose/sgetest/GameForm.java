package ch.nexpose.sgetest;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.StoryBoard;
import ch.nexpose.sge.ui.GameScene;
import ch.nexpose.sgetest.gravity.GravityStory;
import ch.nexpose.sgetest.jump.JumpStory;
import ch.nexpose.sgetest.space.MenuStory;
import ch.nexpose.sgetest.space.SpaceGameStory;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by cansik on 08/04/14.
 */
public class GameForm
{
    private JPanel mainPanel;
    private JPanel gamePanel;
    private SimpleGameEngine2D engine;
    JFrame frame;
    GameScene scene;

    public GameForm()
    {
        gamePanel.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                if("ancestor".equals(evt.getPropertyName()))
                    createGUIBinding();
            }
        });
    }

    public void showForm()
    {
        frame = new JFrame("Starship Game");
        frame.setContentPane(new GameForm().mainPanel);
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
    }

    public void createGUIBinding()
    {
        //Setup scene
        scene = new GameScene(new Dimension(852, 480));
        scene.setBackgroundColor(Color.black);
        scene.setSize(new Dimension(852, 480));
        //scene.setSize(this.gamePanel.getPreferredSize());
        //TODO: Bug with set size

        gamePanel.add(scene);
        gamePanel.updateUI();
        scene.requestFocus();

        setupGame();
    }

    public void setupGame()
    {
        SimpleGameEngine2D engine = new SimpleGameEngine2D(scene);
        StoryBoard board = new StoryBoard();

        MenuStory menu = new MenuStory(engine, board);
        SpaceGameStory game = new SpaceGameStory(engine, board);
        JumpStory jumpStory = new JumpStory(engine);
        GravityStory gravityStory = new GravityStory(engine);

        board.addGameStory(gravityStory);
        board.addGameStory(jumpStory);
        board.addGameStory(menu);
        board.addGameStory(game);

        board.getNextStory().runStory();
    }
}
