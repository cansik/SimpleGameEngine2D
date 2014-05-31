package ch.nexpose.sge.fx.story;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.TexturedObject2D;
import ch.nexpose.sge.story.IGameStory;
import ch.nexpose.sge.ui.GameScene;

/**
 * Created by cansik on 30/05/14.
 */
public class StorySwitcher implements IGameStory
{
    private SimpleGameEngine2D _engine;

    IGameStory currentStory;
    IGameStory newStory;

    public StorySwitcher(GameScene scene, IGameStory currentStory, IGameStory newStory)
    {
        _engine = new SimpleGameEngine2D(scene);
        this.currentStory = currentStory;
        this.newStory = newStory;
    }

    @Override
    public void runStory()
    {
        //get frames
        //TexturedObject2D currentFrame = new TexturedObject2D(_engine.)
    }

    @Override
    public void resumeStory()
    {

    }

    @Override
    public void stopStory()
    {

    }

    @Override
    public void nextFrame()
    {

    }
}
