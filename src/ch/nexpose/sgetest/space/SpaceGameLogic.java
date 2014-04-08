package ch.nexpose.sgetest.space;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.SimpleGameLogic;
import ch.nexpose.sge.ui.GameScene;

/**
 * Created by cansik on 08/04/14.
 */
public class SpaceGameLogic implements SimpleGameLogic
{
    SimpleGameEngine2D engine;

    public SpaceGameLogic(SimpleGameEngine2D engine)
    {
        this.engine = engine;
        this.engine.addGameLogic(this);
    }

    @Override
    public void nextGameStep()
    {

    }
}
