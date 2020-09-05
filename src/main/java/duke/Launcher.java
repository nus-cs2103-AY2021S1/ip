package duke;

import javafx.application.Application;

/**
 * Class from which to launch the application in its GUI form.
 */
public class Launcher {

    /**
     * Launches the GUI version of the Duke app.
     *
     * @param args CLI arguments. None required.
     */
    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }

}
