package duke;


import javafx.application.Application;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("Launching Duke!!!");
        Application.launch(Duke.class, args);
        System.out.println("Duke processes finished.");
    }
}
