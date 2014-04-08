/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge;

import ch.nexpose.sge.objects.Object2D;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.nexpose.sge.ui.GameScene;

/**
 *
 * @author cansik
 */
public class SimpleGameEngine2D implements Runnable {
    final int FRAMERATE = 40; //24 Frames;
    
    boolean running;
    GameScene scene;
    BufferedImage frame;

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

    public ArrayList<Object2D> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(ArrayList<Object2D> gameObjects) {
        this.gameObjects = gameObjects;
    }
    
    ArrayList<Object2D> gameObjects;
    Thread frameDrawer;
    
    public SimpleGameEngine2D(GameScene scene)
    {
        gameObjects = new ArrayList<Object2D>();
        this.scene = scene;
    }
    
    public void startEngine()
    {
        running = true;
        frameDrawer = new Thread(this);
        frameDrawer.start();
    }
    
    public void stopEngine()
    {
        running = false;
    }
    
    public void run()
    {
        while(running)
        {   
            game();
            Graphics2D g = this.getFrame();
            repaint(g);
            g.dispose();
           
            scene.setBackgroundImage(frame);
            scene.repaint();
            
            try {
                Thread.sleep(FRAMERATE);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimpleGameEngine2D.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private Graphics2D getFrame()
    {
        frame = new BufferedImage(this.scene.getWidth(), this.scene.getHeight(), BufferedImage.TYPE_INT_ARGB);
        return frame.createGraphics();
    }
    
    private void game()
    {
       for(Collision c : CollisionDetector.detectCollisions(gameObjects))
       {
           System.out.println("collision: " + c.firstObject + " -> " + c.secondObject);    
       }
    }
    
    private void repaint(Graphics2D g)
    {
        for(int i = 0; i < gameObjects.size(); i++)
        {
            if(gameObjects.get(i).isAlive())
            {
                gameObjects.get(i).move();
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
