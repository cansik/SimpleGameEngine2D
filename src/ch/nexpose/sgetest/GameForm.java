package ch.nexpose.sgetest;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.ui.GameScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * Created by cansik on 08/04/14.
 */
public class GameForm
{
    private JPanel mainPanel;
    private JButton btnStart;
    private JPanel gamePanel;
    private SimpleGameEngine2D engine;
    JFrame frame;
    GameScene scene;

    public GameForm()
    {

        btnStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                scene = new GameScene();
                scene.setBackgroundColor(Color.black);
                scene.setSize(gamePanel.getWidth(), gamePanel.getHeight());

                gamePanel.add(scene);
                gamePanel.updateUI();

                setupGame();
            }
        });
    }

    public void showForm()
    {
        frame = new JFrame("Starship Game");
        frame.setContentPane(new GameForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setupGame()
    {
        SimpleGameEngine2D engine = new SimpleGameEngine2D(scene);
        engine.startEngine();
    }
}
