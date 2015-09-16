package ch.nexpose.sge.controls.leap;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;

import java.util.Vector;

/**
 * Created by cansik on 15/04/15.
 */
public class LeapMotionTracker  extends Listener {

    Controller controller;
    volatile Frame lastFrame;

    public void onConnect(Controller controller) {
        System.out.println("LeapMotion Connected");
    }

    public void onFrame(Controller controller) {
        lastFrame = controller.frame();
    }

    public void startTracker()
    {
        controller = new Controller();
        controller.addListener(this);
    }

    public void stopTracker()
    {
        controller.removeListener(this);
    }

    public Frame getLastFrame() {
        lastFrame = lastFrame;
        return lastFrame;
    }
}
