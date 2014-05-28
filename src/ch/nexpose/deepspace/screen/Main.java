package ch.nexpose.deepspace.screen;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        GameForm f = new GameForm();
        f.showForm();
    }
}
