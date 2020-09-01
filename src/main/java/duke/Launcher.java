package duke;

import javafx.application.Application;

/**
 * Launches the programme.
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            Application.launch(Main.class, args);
        } catch (Exception e) {
            //System.out.println(e.getCause()+e.getMessage());
            e.printStackTrace();
            e.printStackTrace(System.out);
        }
    }
}
