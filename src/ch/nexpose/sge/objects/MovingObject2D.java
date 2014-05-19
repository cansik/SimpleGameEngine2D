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
        else
            putOnScene();
    }

    public Point getNextLocation()
    {
        return getNextLocation(true);
    }

    public Point getNextLocation(boolean track)
    {
        int dx = 0, dy = 0;
        int ldx = dx, ldy = dy;

        //OLD steering approach deprecated!
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

        //NEW steering approach!
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

        Point nextPoint = new Point(this.location.x + dx, this.location.y + dy);

        if(!track)
        {
            dx = ldx;
            dy = ldy;
        }

        return nextPoint;
    }

    public void putOnScene()
    {
        Dimension sceneSize = this.getEngine().getScene().getViewPortSize();

        if(getLocation().x < 0)
            setLocation(new Point(0, getLocation().y));

        if(getLocation().y < 0)
            setLocation(new Point(getLocation().x, 0));

        if(getLocation().x + this.getSize().width >= sceneSize.width)
            setLocation(new Point(sceneSize.width - this.getSize().width, getLocation().y));

        if(getLocation().y + this.getSize().height >= sceneSize.height)
            setLocation(new Point(getLocation().x, sceneSize.height - this.getSize().height));
    }

    public boolean isOnScene()
    {
        //check bounds
        Point futureLocation = this.getNextLocation(false);
        Dimension sceneSize = this.getEngine().getScene().getViewPortSize();

        //TODO: check if width <= width is the right comaprer (not only < ?)
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
