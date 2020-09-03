package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;

import duke.exception.DukeException;

import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Represents the chatbot Duke.
 */
public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskList tasks;
    private Storage storage;

    /**
     * Boots up Duke.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        String basePath = System.getProperty("user.dir");
        Path path = Paths.get(basePath, "data", "tasks.txt");
        storage = Storage.setup(path);
        tasks = storage.readData();
    }

    String displayWelcome() {
        return ui.displayWelcome();
    }

    /**
     * Exits the program.
     */
    void exit() {
        storage.updateData(tasks);
    }

    /**
     * Parses and validates user input before executing the command and
     * returning a response.
     */
    String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            if (c.isExit()) {
                exit();
            }
            return c.execute(tasks, ui);
        } catch (DukeException e) {
            return ui.displayError(e.toString());
        }
    }
}
