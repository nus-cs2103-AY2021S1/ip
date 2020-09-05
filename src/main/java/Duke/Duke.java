package duke;

import command.Command;
import exception.DukeException;

/**
 * Represents a CLI Program known as Duke.
 *
 * @author Francis Hodianto
 * @version 0.1
 */
public class Duke {

    private static final String DEFAULT_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Duke.
     *
     * @param filePath The path of the file used for storing saved tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Creates an instance of Duke.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(DEFAULT_PATH);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gives the user responses based on the user input.
     *
     * @param input The user input.
     * @return A string representing Duke's response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            assert command != null : "command should not be null";
            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Initiates Duke on the command line.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command command = Parser.parse(fullCommand);
                assert command != null : "command should not be null";
                ui.showMessage(command.execute(tasks, storage));
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(DEFAULT_PATH).run();
    }
}
