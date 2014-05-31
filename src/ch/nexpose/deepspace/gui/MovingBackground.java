package ch.nexpose.deepspace.gui;

import ch.nexpose.sge.controls.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.TexturedObject2D;

import java.awt.*;

/**
 * Created by cansik on 12/04/14.
 */
public class MovingBackground extends TexturedObject2D
{
    TexturedObject2D cloneImage;

    public MovingBackground(SimpleGameEngine2D engine, Image backgroundImage)
    {
        super(engine, backgroundImage);
        this.setDirection(Direction.LEFT);
        this.setSpeed(1);
        //this.setSize(new Dimension(backgroundImage.getWidth(engine.getScene()), backgroundImage.getHeight(engine.getScene())));
        this.setCollisionable(false);
        this.setColor(Color.MAGENTA);

        cloneImage = new TexturedObject2D(engine, backgroundImage);
        cloneImage.setDirection(Direction.LEFT);
        cloneImage.setSpeed(1);
        cloneImage.setCollisionable(false);
        //cloneImage.setSize(new Dimension(backgroundImage.getWidth(engine.getScene()), backgroundImage.getHeight(engine.getScene())));
        this.setColor(Color.blue);

        engine.addGameObject(cloneImage);
        setLocationOrder(this, cloneImage);

        //Todo: get image size doesn't work!
    }

    /**
     * Switches location of two images.
     * @param object1
     * @param object2
     */
    private void setLocationOrder(TexturedObject2D object1, TexturedObject2D object2)
    {
        object1.setLocation(new Point(0, 0));
        object2.setLocation(new Point(object1.getLocation().x + object2.getSize().width, object1.getLocation().y));
    }

    @Override
    public void action()
    {
        super.action();

        if(getLocation().x + this.getSize().width < 0)
        {
            setLocationOrder(cloneImage, this);
        }

        if(cloneImage.getLocation().x + cloneImage.getSize().width < 0)
        {
            setLocationOrder(this, cloneImage);
        }
    }
}
