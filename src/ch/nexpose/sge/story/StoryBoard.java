package ch.nexpose.sge.story;

import java.util.ArrayList;

/**
 * Created by cansik on 08/04/14.
 */
public class StoryBoard
{
    int currentStory = 0;
    ArrayList<IGameStory> storyBoard;

    public StoryBoard()
    {
        storyBoard = new ArrayList<IGameStory>();
    }

    /**
     * Adds a story to the storyboard.
     * @param story
     */
    public void addGameStory(IGameStory story)
    {
        this.storyBoard.add(story);
    }

    /**
     * Returns the next story from the storyboard.
     * @return
     */
    public IGameStory getNextStory()
    {
        return storyBoard.get(currentStory++);
    }

}
