package duke;

import javafx.application.Application;

public class Launcher {
    /**
     * Launcher class to workaround classpath issues
     *
     */
    public static void main(String[] args) {
        Application.launch(MainGUI.class, args);
    }
}
