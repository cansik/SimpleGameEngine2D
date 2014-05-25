package ch.nexpose.sge;

import ch.nexpose.sge.objects.TextObject2D;
import ch.nexpose.sge.story.IGameStory;

import java.awt.*;

/**
 * Created by cansik on 09/04/14.
 */
class EngineDebugger implements IGameStory
{
    SimpleGameEngine2D engine;
    TextObject2D debugText;
    int frameCounter = 0;
    long frameCounterStartTime;

    public EngineDebugger(SimpleGameEngine2D engine)
    {
        this.engine = engine;
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
        debugText = new TextObject2D(engine, "FPS: detecting");
        debugText.setColor(Color.white);
        debugText.setLocation(new Point(30, 30));
        debugText.setCollisionable(false);
        debugText.setFont(new Font("Verdana", Font.PLAIN, 12));

        this.engine.addGameObject(debugText);

        frameCounterStartTime = System.currentTimeMillis();
    }

    @Override
    public void resumeStory()
    {
        engine.startEngine();
    }

    @Override
    public void stopStory()
    {
        engine.stopEngine();
    }
}
