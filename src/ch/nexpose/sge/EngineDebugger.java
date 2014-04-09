package ch.nexpose.sge;

import ch.nexpose.sge.objects.TextObject2D;

import java.awt.*;
import java.util.Date;

/**
 * Created by cansik on 09/04/14.
 */
class EngineDebugger implements GameStory
{
    SimpleGameEngine2D engine;
    TextObject2D debugText;
    int frameCounter = 0;
    long frameCounterStartTime;

    public EngineDebugger(SimpleGameEngine2D engine)
    {
        this.engine = engine;
        debugText = new TextObject2D("FPS: detecting");
        debugText.setColor(Color.white);
        debugText.setEngine(engine);
        debugText.setLocation(new Point(10, 20));
        debugText.setCollisionable(false);
        debugText.setFont(new Font("Courier New", Font.PLAIN, 20));

        this.engine.addGameObject(debugText);

        frameCounterStartTime = System.currentTimeMillis();
    }

    @Override
    public void nextFrame()
    {
        if(System.currentTimeMillis() - frameCounterStartTime >= 1000)
        {
            debugText.setText("FPS: " + frameCounter);
            frameCounter = 1;
            frameCounterStartTime = System.currentTimeMillis();
        }
        else
        {
            frameCounter++;
        }
    }

    @Override
    public void runStory()
    {

    }
}
