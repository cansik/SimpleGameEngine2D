package ch.nexpose.sgetest.space;

import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.story.IGameStory;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.story.StoryBoard;
import ch.nexpose.sge.objects.AnimatedObject2D;
import ch.nexpose.sge.objects.FlashingText;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by cansik on 08/04/14.
 */
public class MenuStory implements IGameStory
{
    StoryBoard storyBoard;
    SimpleGameEngine2D engine;
    boolean keyPressed = false;
    AnimatedObject2D aniDemo;

    public MenuStory(SimpleGameEngine2D engine, StoryBoard storyBoard)
    {
        this.engine = new SimpleGameEngine2D(engine.getScene());
        this.storyBoard = storyBoard;
    }

    private void initMenu()
    {
        FlashingText startText = new FlashingText(engine, "press space to start!");
        startText.setColor(Color.green);
        startText.setLocation(new Point(((int)this.engine.getScene().getViewPortSize().getWidth() / 2) - (int)(startText.getSize().width / 2),
                (int)((int)this.engine.getScene().getViewPortSize().getHeight() / 2) - (int)(startText.getSize().height / 2)));

        AnimatedStarship animatedStarship = new AnimatedStarship(engine);
        animatedStarship.setLocation(new Point(0 - animatedStarship.getSize().width, 200));
        animatedStarship.setDirection(Direction.RIGHT);


        //DEMO
        Image rocket = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/rocket.png"));
        Image rocket1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/rocket1.png"));
        Image rocket2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/rocket2.png"));

        aniDemo = new AnimatedObject2D(engine, rocket);
        aniDemo.getAnimation().setSpeed(2);
        aniDemo.getAnimation().getFrames().add(rocket1);
        aniDemo.getAnimation().getFrames().add(rocket2);
        aniDemo.getAnimation().getFrames().add(rocket1);
        aniDemo.getAnimation().getFrames().add(rocket2);
        aniDemo.setSize(new Dimension(50, 50));

        engine.addGameObject(animatedStarship);
        engine.addGameObject(startText);
        engine.addGameObject(aniDemo);

        engine.addGameStory(this);
    }

    @Override
    public void runStory()
    {
        initMenu();
        engine.startEngine();
    }

    @Override
    public void nextFrame()
    {
        if(engine.getInputTracker().isKeyPressed(KeyEvent.VK_SPACE))
        {
            engine.stopEngine();
            engine.getScene().removeAll();
            storyBoard.getNextStory().runStory();
        }

        if(engine.getInputTracker().isKeyPressed(KeyEvent.VK_A))
        {
            aniDemo.playAnimation();
        }
    }

    @Override
    public void resumeStory()
    {
        engine.startEngine();
    }

    @Override
    public void stopStory()
    {
        engine.stopEngine();
    }
}
