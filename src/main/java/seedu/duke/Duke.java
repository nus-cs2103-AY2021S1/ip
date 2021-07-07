package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

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
            ArrayList<Task> emptyList = new ArrayList<>();
            ArrayList<Task> listFromStorage = storage.loadFromStorage(emptyList);
            taskLists = new TaskList(listFromStorage);
            parser = new Parser(taskLists);
            ui = new Ui(parser);
        } catch (IOException e) {
            parser = new Parser(taskLists);
            ui = new Ui(parser);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the output string for GUI.
     *
     * @param input user inputs command.
     * @return String output for GUI.
     */
    public String getResponse(String input) {
        return ui.getUserInput(input);
    }

}
