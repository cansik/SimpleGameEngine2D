package ch.nexpose.deepspace.gui;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.TextObject2D;

/**
 * Created by cansik on 09/05/14.
 */
public class ScoreText extends TextObject2D
{
    int points;
    int lifes;
    int level;
    int maxlifes;
    StringBuilder builder;

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getLifes()
    {
        return lifes;
    }

    public void setLifes(int lifes)
    {
        this.lifes = lifes;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public int getMaxlifes()
    {
        return maxlifes;
    }

    public void setMaxlifes(int maxlifes)
    {
        this.maxlifes = maxlifes;
    }

    public ScoreText(SimpleGameEngine2D engine)
    {
        super(engine);
    }

    @Override
    public void action()
    {
        builder = new StringBuilder();

        builder.append("Level: " + level + " | ");
        builder.append("Points: " + points + " | ");
        builder.append("Life: ");

        for(int i = 0; i < lifes; i++)
            builder.append("\u2665");

        /*
        for (int i = 0; i < maxlifes; i++)
        {
            if (i + 1 <= lifes)
                builder.append("=");
            else
                builder.append("  ");
        }
        builder.append("]");
        */
        //builder.append("\u2665");

        this.setText(builder.toString());
    }
}
