package duke;

import duke.gui.Main;
import javafx.application.Application;

/**
 * The main entry-point to the Duke application.
 */
public class Launcher {

    /**
     * Launches the Duke application through the Main GUI file.
     *
     * @param args The supplied arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
