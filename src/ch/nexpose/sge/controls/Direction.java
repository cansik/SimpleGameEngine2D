/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.nexpose.sge.controls;

/**
 * Moving direction of an object.
 * @author cansik
 */
public enum Direction
{
    LEFT,
    RIGHT,
    UP,
    DOWN,
    NONE;

    public final int flag;

    Direction() {
        this.flag = 1 << this.ordinal();
    }
}
