/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.nexpose.sge.objects;

import java.awt.Point;
import ch.nexpose.sge.Direction;

/**
 *
 * @author cansik
 */
public abstract class MovingObject2D extends Object2D {

    int speed;
    Direction direction;

    public MovingObject2D() {
        super();
        this.direction = Direction.NONE;
        this.speed = 0;
    }

    @Override
    public void move() {
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

        this.location = new Point(this.location.x + dx, this.location.y + dy);
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
