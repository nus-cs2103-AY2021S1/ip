package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a Duke.
     *
     * @param filePath Location of file to load tasks from.
     * @return a duke for the current program.
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser parser = new Parser(fullCommand);
                Command c = parser.parse();
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine(); // show the divider line ("_______")
            }
        }
    }

    /**
     * Runs the program with each of the strings in the argument. For testing purposes.
     * @param input test commands for Duke to carry out.
     */
    public void testRun(String[] input) {
        ui.showWelcome();
        for (String line : input) {
            try {
                Parser parser = new Parser(line);
                Command c = parser.parse();
                c.execute(tasks, ui, storage);
                if (c.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine(); // show the divider line ("_______")
            }
        }
    }

    public static void main(String[] args) {
         new Duke("data/tasks.txt").run();
    }
}
