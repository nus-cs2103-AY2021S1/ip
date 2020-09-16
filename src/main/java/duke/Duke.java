package duke;

import duke.command.Command;

import duke.exception.DukeException;

import duke.parser.Parser;

import duke.task.TaskList;

import duke.ui.Ui;

import static duke.Storage.STORAGE_FILEPATH;
import static duke.ui.Message.MESSAGE_LOADING_ERROR;
import static duke.ui.Message.MESSAGE_WELCOME;
import static duke.ui.Message.showError;

/**
 * Main program of the Duke program.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke() {
        this(STORAGE_FILEPATH);
    }

    /**
     * Constructs a <code>Duke</code> Object.
     *
     * @param filePath The file path where the data is (to be) stored.
     */
    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printResponse(MESSAGE_LOADING_ERROR);
            this.tasks = new TaskList();
        }
    }

    /**
     * Main entry point of the application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke("./data/duke.txt").run();
    }

    private void run() {
        ui.printResponse(MESSAGE_WELCOME);
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                ui.printResponse(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printResponse(showError(e.getMessage()));
            }
        }
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return showError(e.getMessage());
        }
    }
}
