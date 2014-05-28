package ch.nexpose.sge.story;

import ch.nexpose.sge.INextFrameListener;

/**
 * Created by cansik on 08/04/14.
 */
public interface IGameStory extends INextFrameListener
{
    public void runStory();
    public void resumeStory();
    public void stopStory();
}
