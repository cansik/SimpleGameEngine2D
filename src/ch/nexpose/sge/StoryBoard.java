package ch.nexpose.sge;

import java.util.ArrayList;

/**
 * Created by cansik on 08/04/14.
 */
public class StoryBoard
{
    int currentStory = 0;
    ArrayList<GameStory> storyBoard;

    public StoryBoard()
    {
        storyBoard = new ArrayList<GameStory>();
    }

    public void addGameStory(GameStory story)
    {
        this.storyBoard.add(story);
    }

    public GameStory getNextStory()
    {
        return storyBoard.get(currentStory++);
    }
}
