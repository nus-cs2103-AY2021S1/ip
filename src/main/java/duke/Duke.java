package duke;

import duke.task.TaskList;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a chat bot that functions as a task manager.
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Creates and initialises a new Duke object that has a Ui, Storage and TaskList object.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = this.storage.readFile();
    }

    /**
     * Executes a DukeBot session for the bot to perform its intended functions.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initialises a new DukeBot session.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // start a new session with JonasBot
        Duke session = new Duke();

        // execute the bot to perform intended functions
        session.run();
    }

}
