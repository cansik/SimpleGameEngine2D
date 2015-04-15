package ch.nexpose.race;

import ch.nexpose.deepspace.screen.RandomGenerator;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.story.IGameStory;
import ch.nexpose.sge.ui.GameScene;
import ch.nexpose.soccer.stories.objects.SoccerBall;
import ch.nexpose.soccer.stories.objects.SoccerField;
import ch.nexpose.soccer.stories.objects.SoccerPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by cansik on 23/09/14.
 */
public class RaceStory implements IGameStory
    {
        SimpleGameEngine2D engine;
        Car raceCar;

        public RaceStory(GameScene scene)
        {
            engine = new SimpleGameEngine2D(scene);
            engine.addNextFrameListener(this);

            //set viewport size
            scene.setViewPortSize(new Dimension(1280, 720));
        }

        @Override
        public void runStory()
        {
            raceCar = new Car(engine);

            engine.addGameObject(raceCar);
            engine.startEngine();
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
            if(engine.getInputTracker().isKeyPressed(KeyEvent.VK_UP))
                raceCar.push(5, Direction.UP);

            if(engine.getInputTracker().isKeyPressed(KeyEvent.VK_DOWN))
                raceCar.push(5, Direction.DOWN);
        }
}
