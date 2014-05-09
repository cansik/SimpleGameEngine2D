/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.nexpose.sge.objects;

import java.awt.*;
import java.util.HashSet;

import ch.nexpose.sge.Direction;
import ch.nexpose.sge.SimpleGameEngine2D;

/**
 *
 * @author cansik
 */
public class MovingObject2D extends Object2D {

    int speed;
    Direction direction;
    boolean bordercheck;
    HashSet<Direction> directions;

    public HashSet<Direction> getDirections()
    {
        return directions;
    }

    public MovingObject2D(SimpleGameEngine2D engine) {
        super(engine);
        this.direction = Direction.NONE;
        this.speed = 0;
        directions = new HashSet<Direction>();
    }

    @Override
    public void action() {

        if(!isBordercheck() || isOnScene())
            this.location = getNextLocation();
    }

    public Point getNextLocation()
    {
        int dx = 0, dy = 0;

        //OLD
        switch (this.direction) {
            case LEFT:
                dx = -1 * speed;
                break;
            case RIGHT:
                dx = speed;
                break;
            case UP:
                dy = -1 * speed;
                break;
            case DOWN:
                dy = speed;
                break;
            case NONE:
                //nothing
                break;
        }

        //NEW
        if (this.getDirections().contains(Direction.LEFT))
        {
            dx = -1 * speed;
        }

        if (this.getDirections().contains(Direction.RIGHT))
        {
            dx = speed;
        }

        if (this.getDirections().contains(Direction.UP))
        {
            dy = -1 * speed;
        }

        if (this.getDirections().contains(Direction.DOWN))
        {
            dy = speed;
        }

        return new Point(this.location.x + dx, this.location.y + dy);
    }

    public boolean isOnScene()
    {
        //check bounds
        //TODO: getNextLocation does decrease speed
        Point futureLocation = this.getNextLocation();
        Dimension sceneSize = this.getEngine().getScene().getViewPortSize();

        return ((futureLocation.x >= 0 && futureLocation.x + this.getSize().width <= sceneSize.width) &&
                (futureLocation.y >= 0 && futureLocation.y + this.getSize().height <= sceneSize.height));
    }

    public boolean isBordercheck()
    {
        return bordercheck;
    }

    public void setBordercheck(boolean bordercheck)
    {
        this.bordercheck = bordercheck;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
