package duke;

import duke.ui.Cli;
import duke.ui.Gui;
import duke.ui.Ui;
import javafx.application.Application;

public class Main {
    /**
     * Main function/entrypoint. Will create a new Duke instance and begin interaction with the user immediately.
     * Takes in no command line arguments.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0] == "--cli") {
            Ui ui = new Cli();
            Duke duke = new Duke(ui);
            while (ui.isActive()) {
                duke.nextIteration();
            }
        } else {
            Application.launch(Gui.class, args);
        }
    }
}
