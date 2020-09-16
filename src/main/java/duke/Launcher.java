package duke;

import javafx.application.Application;

public class Launcher {
    /** Empty constructor. */
    private Launcher() { }
    /** Launches the Ui.
     * @param args input string (not used)
     */
    public static void main(String[] args) {
        Application.launch(MainWithUi.class, args);
    }
}
