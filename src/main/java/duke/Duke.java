package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents the driver class to run the Duke programme.
 */
public class Duke {

    /**
     * A storage instance to load and save tasks.
     */
    private final Storage storage;

    /**
     * A task manager to handle task operations.
     */
    private TaskManager manager;

    /**
     * A ui instance to handle user interactions.
     */
    private final Ui ui;

    /**
     * Initializes all class variables
     * @param filePath the file path for the storage
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            manager = new TaskManager(storage.loadTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            manager = new TaskManager();
        }
    }

    /**
     * Runs and starts the programme.
     */
    public void run() {
        ui.showWelcomeMessage();   
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parseCommand(fullCommand);
                command.execute(manager, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
