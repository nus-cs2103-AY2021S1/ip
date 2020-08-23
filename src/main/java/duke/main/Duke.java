package duke.main;

import duke.command.Command;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.parser.Parser;


import java.io.FileNotFoundException;

/**
 * Encapsulates the core class which coordinates with all other key classes to
 * take in input, process it and produce the output that the Duke program displays
 * to its users.
 */
public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Creates a Duke object which holds a file path and uses it to load
     * the text file from the hard drive or create one if it does not yet exist.
     * It also creates new Ui and Storage objects for functionality.
     * @param filePath The path where the text file is located.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.loadFileError();
            storage.createFile();
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.loadDateError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program until the bye command is entered, and until
     * then it takes in commands and processes them to provide output to
     * the user.
     */
    public void run() {
        // display starting UI
        ui.showStartMessage();
        ui.showCurrentTasks(tasks.getTaskList());

        Parser parser = new Parser(ui);
        boolean isDone = false;
        while (!isDone) {
            try {
                String command = ui.readCommand();
                Command c = parser.parse(command);
                c.execute(tasks, ui, storage);
                isDone = c.isDone();
            } catch (DukeException ex) {
                ui.printExceptions(ex);
            }

        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
