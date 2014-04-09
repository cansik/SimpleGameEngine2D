/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.nexpose.sge.objects;

import java.awt.*;

import ch.nexpose.sge.Direction;

/**
 *
 * @author cansik
 */
public class MovingObject2D extends Object2D {

    int speed;
    Direction direction;
    boolean bordercheck;

    public MovingObject2D() {
        super();
        this.direction = Direction.NONE;
        this.speed = 0;
    }

    @Override
    public void move() {

        if(!isBordercheck() || isOnScene())
            this.location = getNextLocation();
    }

    public Point getNextLocation()
    {
        int dx = 0, dy = 0;

        switch (this.direction) {
            case LEFT:
                dx = -1 * speed;
                break;
            case RIGHT:
                dx = 1 * speed;
                break;
            case UP:
                dy = -1 * speed;
                break;
            case DOWN:
                dy = 1 * speed;
                break;
            case NONE:
                //nothing
                break;
        }

        return new Point(this.location.x + dx, this.location.y + dy);
    }

    public boolean isOnScene()
    {
        //check bounds
        Point futureLocation = this.getNextLocation();
        Dimension sceneSize = this.getEngine().getScene().getSize();

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
