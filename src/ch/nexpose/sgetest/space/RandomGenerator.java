package ch.nexpose.sgetest.space;

import java.util.Random;

/**
 * Created by cansik on 12/04/14.
 */
public class RandomGenerator
{
    public static int randInt(int min, int max)
    {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static boolean getBooleanByChance(int chance)
    {
        if(chance > 0)
            return randInt(1, chance) == 1;
        else
            return false;
    }
}
