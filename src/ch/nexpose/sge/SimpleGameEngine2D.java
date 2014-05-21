/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge;

import ch.nexpose.sge.collisions.CollisionDetector;
import ch.nexpose.sge.controls.InputTracker;
import ch.nexpose.sge.objects.Object2D;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.nexpose.sge.ui.GameScene;

/**
 *
 * @author cansik
 */
public class SimpleGameEngine2D implements Runnable {
    final int FRAMERATE = 48;
    
    boolean running;
    GameScene scene;
    BufferedImage frame;
    CollisionDetector collisionDetector;
    List<IGameStory> gameStories;
    InputTracker inputTracker;

    public InputTracker getInputTracker()
    {
        return inputTracker;
    }

    public GameScene getScene() {
        return scene;
    }

    public void setScene(GameScene scene) {
        this.scene = scene;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void addGameObject(Object2D gameObject) {
        gameObjects.add(gameObject);
    }

    public void addGameStory(IGameStory story) {
        gameStories.add(story);
    }

    public List<IGameStory> getGameStories()
    {
        return gameStories;
    }

    ArrayList<Object2D> gameObjects;
    Thread frameDrawer;
    
    public SimpleGameEngine2D(GameScene scene)
    {
        this.scene = scene;

        gameObjects = new ArrayList<Object2D>();
        collisionDetector = new CollisionDetector();
        gameStories = new ArrayList<IGameStory>();
        inputTracker = new InputTracker(scene);
    }
    
    public void startEngine()
    {
        running = true;
        inputTracker.openInputManager();
        frameDrawer = new Thread(this);
        frameDrawer.start();
    }
    
    public void stopEngine()
    {
        inputTracker.closeInputManager();
        running = false;
    }

    public void resetEngine()
    {
        gameObjects.clear();
    }
    
    public void run()
    {
        //debug
        EngineDebugger debugger = new EngineDebugger(this);
        this.addGameStory(debugger);

        while(running)
        {
            //collision detection
            collisionDetector.detectCollisions(gameObjects);

            //run gamelogic
            NotifyGameLogic();

            //repainting
            Graphics2D g = this.getFrame();
            repaint(g);
            g.dispose();
           
            scene.setBackgroundImage(frame);
            scene.repaint();
            
            try {
                Thread.sleep((int)1000 / FRAMERATE);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimpleGameEngine2D.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void NotifyGameLogic()
    {
        // Notify every game logic attached to this engine
        if(isRunning())
        {
            for (IGameStory IGameStory : gameStories)
                IGameStory.nextFrame();
        }
    }
    
    private Graphics2D getFrame()
    {
        frame = new BufferedImage((int)this.scene.getViewPortSize().getWidth(), (int)this.scene.getViewPortSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
        return frame.createGraphics();
    }
    
    private void repaint(Graphics2D g)
    {
        for(int i = 0; i < gameObjects.size(); i++)
        {
            if(gameObjects.get(i).isAlive())
            {
                gameObjects.get(i).action();
                gameObjects.get(i).paint(g);
            }
            else
            {
                gameObjects.remove(gameObjects.get(i));
                i--;
            }
        }
    }
}
