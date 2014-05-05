package ch.nexpose.sgetest.jump;

import ch.nexpose.sge.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;

/**
 * Created by cansik on 10/04/14.
 */
public class JumpStory implements IGameStory
{
    SimpleGameEngine2D engine;


    public JumpStory(SimpleGameEngine2D engine)
    {
        this.engine = engine;
    }

    @Override
    public void nextFrame()
    {

    }

    @Override
    public void runStory()
    {
        Player p = new Player(engine);
        engine.addGameObject(p);
        engine.startEngine();
    }
}
