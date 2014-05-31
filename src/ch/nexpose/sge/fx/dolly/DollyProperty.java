package ch.nexpose.sge.fx.dolly;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by cansik on 28/05/14.
 */
public class DollyProperty
{
    String fieldName;
    String methodName;

    float speed;
    float currentValue;

    int time;
    int startValue;
    int endValue;
    int delay;
    int frameCounter;

    LoopType loopType;

    Object mainObject;

    public DollyProperty()
    {

    }

    public DollyProperty(String fieldName,  int time, int startValue, int endValue)
    {
        this(fieldName, time, startValue, endValue, 0);
    }

    public DollyProperty(String fieldName,  int time, int startValue, int endValue, int delay)
    {
        this(fieldName, "", time, startValue, endValue, delay);
    }

    public DollyProperty(String fieldName, String methodName,  int time, int startValue, int endValue)
    {
        this(fieldName, methodName, time, startValue, endValue, 0);
    }

    public DollyProperty(String fieldName, String methodName,  int time, int startValue, int endValue, int delay)
    {
        this.fieldName = fieldName;
        this.time = time;
        this.startValue = startValue;
        this.endValue = endValue;
        this.methodName = methodName;
        this.delay = delay;

        this.speed = Math.abs(startValue - endValue) / (float)time;

        this.loopType = LoopType.NoLoop;
    }

    public LoopType getLoopType()
    {
        return loopType;
    }

    public void setLoopType(LoopType loopType)
    {
        this.loopType = loopType;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public int getDelay()
    {
        return delay;
    }

    public void setDelay(int delay)
    {
        this.delay = delay;
    }

    public Object getMainObject()
    {
        return mainObject;
    }

    public void setMainObject(Object mainObject)
    {
        this.mainObject = mainObject;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public int getStartValue()
    {
        return startValue;
    }

    public void setStartValue(int startValue)
    {
        this.startValue = startValue;
    }

    public int getEndValue()
    {
        return endValue;
    }

    public void setEndValue(int endValue)
    {
        this.endValue = endValue;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public void reset()
    {
        frameCounter = 0;
        currentValue = startValue;
        if(startValue - endValue > 0)
            speed *= (-1);
    }

    /**
     * Runs the next step of the dolly animation.
     */
    public void nextStep()
    {
        int currentValueAsInt = Math.round(currentValue);

        if(frameCounter++ <= time)
        {
            //next movement
            this.currentValue += speed;

            if (methodName.equals(""))
                setValue(mainObject, currentValueAsInt);
            else
                setValue(runMethodOnObject(), currentValueAsInt);
        }
        else
        {
            //finished
            switch (loopType)
            {
                case NoLoop:
                    //do nothing
                    //Todo: implement is running!
                    break;

                case Forward:
                    reset();
                    break;

                case PingPong:
                    int tempEndValue = endValue;
                    endValue = startValue;
                    startValue = tempEndValue;

                    reset();

                    break;
            }
        }
    }

    /**
     * Sets the value of the main object.
     * @param object
     * @param value
     * @return
     */
    private boolean setValue(Object object, Object value)
    {
        Class<?> clazz = object.getClass();
        boolean trying = true;

        while (clazz != null && trying)
        {
            try
            {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, value);
                return true;
            } catch (NoSuchFieldException e)
            {
                System.out.println(e.toString());
                clazz = clazz.getSuperclass();
            } catch (Exception e)
            {
            }
        }
        return false;
    }

    /**
     * Invokes a method of the main object by its name.
     * @return
     */
    private Object runMethodOnObject()
    {
        // MZ: Find the correct method
        for (Method method : mainObject.getClass().getMethods())
        {
            if ((method.getName().startsWith("get")) && (method.getName().equals(methodName)))
            {
                // MZ: Method found, run it
                try
                {
                    Object obj = method.invoke(mainObject);
                    return obj;
                } catch (IllegalAccessException e)
                {
                    System.out.println("Could not determine method: " + method.getName());
                } catch (InvocationTargetException e)
                {
                    System.out.println("Could not determine method: " + method.getName());
                }
            }
        }


        return null;
    }
}
