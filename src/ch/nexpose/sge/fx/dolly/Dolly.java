package ch.nexpose.sge.fx.dolly;

import ch.nexpose.sge.INextFrameListener;
import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.AnimatedObject2D;
import ch.nexpose.sge.objects.BaseObject2D;
import ch.nexpose.sge.story.IGameStory;

import java.util.ArrayList;

/**
 * Created by cansik on 28/05/14.
 */
public class Dolly implements INextFrameListener
{
    ArrayList<DollyProperty> properties;
    BaseObject2D mountedObject;
    boolean moving;
    int currentTime = 0;

    public Dolly(BaseObject2D mountedObject)
    {
        properties = new ArrayList<DollyProperty>();
        this.mountedObject = mountedObject;
        this.mountedObject.getEngine().addNextFrameListener(this);
    }

    public boolean isMoving()
    {
        return moving;
    }

    public void setMoving(boolean moving)
    {
        this.moving = moving;
    }

    public BaseObject2D getMountedObject()
    {
        return mountedObject;
    }

    public void setMountedObject(BaseObject2D mountedObject)
    {
        this.mountedObject = mountedObject;
    }

    public ArrayList<DollyProperty> getProperties()
    {
        return properties;
    }

    public void setProperties(ArrayList<DollyProperty> properties)
    {
        this.properties = properties;
    }

    public void addProperty(DollyProperty property)
    {
        property.mainObject = mountedObject;
        this.properties.add(property);
    }

    public void move()
    {
        setMoving(true);

        currentTime = 0;

        for (DollyProperty property : properties)
            property.reset();
    }

    @Override
    public void nextFrame()
    {
        if(isMoving())
        {
            nextStep();
            currentTime++;
        }
    }

    public void nextStep()
    {
        for (DollyProperty property : properties)
            if(property.getDelay() <= currentTime)
                property.nextStep();
    }

}
