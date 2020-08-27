package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;

import java.io.File;

/**
 * A class for duke.
 * Contains the main method to run duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Public constructor of duke.
     *
     * Requires a file path of String type to
     * initiate storage for loading local saved tasks, if a
     * saved list is not found, it will automatically create a new
     * empty list.
     * @param filePath A file path of the task list file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeStorageException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main body of duke.
     *
     * It first shows a welcoming message and will
     * runs until an exit command is detected.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("." + File.separator + "data" + File.separator).run();
    }
}

