package ch.nexpose.sgetest;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.SimpleGameLogic;
import ch.nexpose.sge.objects.StaticObject2D;
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
    Starship ship;

    public GameForm()
    {

        btnStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Stup scene
                scene = new GameScene();
                scene.setBackgroundColor(Color.black);
                scene.setSize(gamePanel.getWidth(), gamePanel.getHeight());

                //Key listener
                scene.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        ship.simpleSteering(evt.getKeyCode());
                    }
                });

                gamePanel.add(scene);
                gamePanel.updateUI();
                scene.requestFocus();

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
        engine.addGameLogic(new SimpleGameLogic()
        {
            @Override
            public void nextGameStep()
            {
                GameLogic();
            }
        });

        ship = new Starship();
        ship.setEngine(engine);

        StaticObject2D victimObject = new StaticObject2D();
        victimObject.setLocation(new Point(400, 200));
        victimObject.setColor(Color.green);
        victimObject.setSize(new Dimension(50, 50));

        engine.addGameObject(ship);
        engine.addGameObject(victimObject);
        engine.startEngine();
    }


    public void GameLogic()
    {
        System.out.println("logic triggered");
    }
}
