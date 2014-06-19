package ch.nexpose.soccer.stories;

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
 * Created by cansik on 19/06/14.
 */
public class MatchStory implements IGameStory
{
    SimpleGameEngine2D engine;

    SoccerField soccerField;
    ArrayList<SoccerPlayer> players;
    SoccerBall ball;

    public MatchStory(GameScene scene)
    {
        engine = new SimpleGameEngine2D(scene);
        engine.addNextFrameListener(this);

        //set vieport size
        scene.setViewPortSize(new Dimension(1280, 720));
    }

    @Override
    public void runStory()
    {
        soccerField = new SoccerField(engine);
        ball = new SoccerBall(engine);

        //test
        ball.setLocation(new Point(300, 300));

        players = new ArrayList<SoccerPlayer>();
        players.add (new SoccerPlayer(engine));
        players.add (new SoccerPlayer(engine));
        players.add (new SoccerPlayer(engine));

        players.get(0).setSelected(true);
        players.get(0).push(1, Direction.RIGHT);

        engine.addGameObject(soccerField);

        for(SoccerPlayer p : players)
            engine.addGameObject(p);

        engine.addGameObject(ball);

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
        //select next player
        if(engine.getInputTracker().isKeyPressed(KeyEvent.VK_S))
            selectNextPlayer();

    }

    private void selectNextPlayer()
    {
        for(SoccerPlayer p : players)
            p.setSelected(false);

        int rnd = RandomGenerator.randInt(0, players.size() - 1);
        players.get(rnd).setSelected(true);
    }
}
