package duke;


import duke.uicomponents.Main;
import javafx.application.Application;

public class Launcher {

    public static void main(String[] args) {

        System.out.println("Launching Duke!!!");
        Application.launch(Main.class, args);
        System.out.println("Duke processes finished.");
    }
}
