package ch.nexpose.sge;

import ch.nexpose.sge.objects.TextObject2D;

import java.awt.*;

/**
 * Created by cansik on 09/04/14.
 */
class EngineDebugger implements GameStory
{
    SimpleGameEngine2D engine;
    TextObject2D frameRateText;

    public EngineDebugger(SimpleGameEngine2D engine)
    {
        this.engine = engine;
        frameRateText = new TextObject2D("FPS:");
        frameRateText.setEngine(engine);
        frameRateText.setLocation(new Point(10, 10));
        frameRateText.setCollisionable(false);

        this.engine.addGameObject(frameRateText);
    }

    @Override
    public void nextFrame()
    {
        frameRateText.setText("FPS:");
    }

    @Override
    public void runStory()
    {

    }
}
