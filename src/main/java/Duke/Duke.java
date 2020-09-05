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
    private static final String DEFAULT_ARCHIVED_PATH = "data/archivedTasks.txt";
    private static final String DEFAULT_MAIN_TASKS_PATH = "data/mainTasks.txt";
    private Storage storage;
    private TaskList mainTasks;
    private TaskList archivedTasks;
    private Ui ui;

    /**
     * Creates an instance of Duke.
     *
     * @param unarchivedFilePath The path of the file used for storing saved tasks.
     */
    public Duke(String unarchivedFilePath, String archivedFilePath) {
        ui = new Ui();
        try {
            storage = new Storage(unarchivedFilePath, archivedFilePath);
            archivedTasks = new TaskList(storage.load(true));
            mainTasks = new TaskList(storage.load(false));
        } catch (DukeException e) {
            ui.showLoadingError();
            archivedTasks = new TaskList();
            mainTasks = new TaskList();
        }
    }

    /**
     * Creates an instance of Duke.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(DEFAULT_MAIN_TASKS_PATH, DEFAULT_ARCHIVED_PATH);
            archivedTasks = new TaskList(storage.load(true));
            mainTasks = new TaskList(storage.load(false));
        } catch (DukeException e) {
            ui.showLoadingError();
            archivedTasks = new TaskList();
            mainTasks = new TaskList();
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
            return command.execute(mainTasks, archivedTasks, storage);
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
                ui.showMessage(command.execute(mainTasks, archivedTasks, storage));
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(DEFAULT_MAIN_TASKS_PATH, DEFAULT_ARCHIVED_PATH).run();
    }
}
