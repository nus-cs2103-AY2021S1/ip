package duke;

import duke.ui.Ui;
/**
 * Duke can take in command and manage tasks.
 */
public class Duke {
    /**
     * The main method in Duke class.
     * Create an Ui object to print the lists of tasks.
     * @param args Unused.
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.run();
    }
}
