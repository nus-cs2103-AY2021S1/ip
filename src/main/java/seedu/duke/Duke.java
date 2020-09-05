package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Main class to run Duke program. Creates and stores tasks such as todo, event and deadline.
 */

public class Duke {
    private Storage storage;
    private TaskList taskLists;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes the Duke class and creates an instance of Storage, TaskList, Parser and Ui.
     */
    public Duke() {
        try {
            storage = new Storage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            taskLists = new TaskList(storage.loadFromStorage(new ArrayList<>()));
            parser = new Parser(taskLists);
            ui = new Ui(parser);
        } catch (IOException e) {
            parser = new Parser(taskLists);
            ui = new Ui(parser);
        }
    }

    /**
     * Returns the output string for GUI.
     *
     * @param input user inputs command.
     * @return String output for GUI.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            new Timer().schedule(new TimerTask() {
                public void run () {
                    Platform.exit();
                    System.exit(0);
                }
            }, 3000);
        }
        return ui.getUserInput(input);
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.intro();
        while (ui.checkDukeStatus()) {
            ui.getNewInput();
        }
        ui.bye();
    }

    /**
     * Standard main method for Java to execute program.
     *
     * @param args Standard Java convection.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
