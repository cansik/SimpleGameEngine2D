package ch.nexpose.sge.story;

/**
 * Created by cansik on 08/04/14.
 */
public interface IGameStory
{
    public void nextFrame();
    public void runStory();
    public void resumeStory();
    public void stopStory();
}
