/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge;

import ch.nexpose.sge.collisions.CollisionDetector;
import ch.nexpose.sge.controls.InputTracker;
import ch.nexpose.sge.objects.BaseObject2D;

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
    public final int FRAMERATE = 48;
    
    boolean running;
    GameScene scene;
    BufferedImage frame;
    CollisionDetector collisionDetector;
    List<INextFrameListener> nextFrameListener;
    InputTracker inputTracker;
    ArrayList<BaseObject2D> gameObjects;
    Thread frameDrawer;

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

    public void addGameObject(BaseObject2D gameObject) {
        gameObjects.add(gameObject);
    }

    public void addNextFrameListener(INextFrameListener listener) {
        nextFrameListener.add(listener);
    }

    public ArrayList<BaseObject2D> getGameObjects()
    {
        return gameObjects;
    }

    public List<INextFrameListener> getNextFrameListener()
    {
        return nextFrameListener;
    }
    
    public SimpleGameEngine2D(GameScene scene)
    {
        this.scene = scene;

        gameObjects = new ArrayList<BaseObject2D>();
        collisionDetector = new CollisionDetector();
        nextFrameListener = new ArrayList<INextFrameListener>();
        inputTracker = new InputTracker(scene);
    }

    /**
     * Starts the thread of the engine.
     */
    public void startEngine()
    {
        running = true;
        inputTracker.openInputManager();
        frameDrawer = new Thread(this);
        frameDrawer.start();
    }

    /**
     * Stops the engine.
     */
    public void stopEngine()
    {
        inputTracker.closeInputManager();
        running = false;
    }

    /**
     * Resets the engine.
     */
    public void resetEngine()
    {
        gameObjects.clear();
    }

    /**
     * Thread run method.
     */
    @Override
    public void run()
    {
        //debug
        EngineDebugger debugger = new EngineDebugger(this);
        this.addNextFrameListener(debugger);
        debugger.runStory();

while(running)
{
    //collision detection
    collisionDetector.detectCollisions(gameObjects);

    //run gamelogic
    notifyNextFrameListener();

    //repainting
    Graphics2D g = this.getFrame();
    repaint(g, true);
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

    /**
     * Notify all associated gamelogics.
     */
    private void notifyNextFrameListener()
    {
        // Notify every game logic attached to this engine
        if(isRunning())
        {
            for (INextFrameListener listener : nextFrameListener)
                listener.nextFrame();
        }
    }

    /**
     * Returns the next plain frame to draw onto.
     * @return
     */
    private Graphics2D getFrame()
    {
        frame = new BufferedImage((int)this.scene.getViewPortSize().getWidth(), (int)this.scene.getViewPortSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
        return frame.createGraphics();
    }

    /**
     * Paint the gameobjects onto the frame.
     * @param g
     */
private void repaint(Graphics2D g, boolean doAction)
{
    for(int i = 0; i < gameObjects.size(); i++)
    {
        if(gameObjects.get(i).isAlive())
        {
            if(doAction)
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

    public BufferedImage GetCurrentFrame()
    {
        Graphics2D g = this.getFrame();
        repaint(g, false);
        g.dispose();

        return frame;
    }
}
