package main.java.duke.main;

import java.io.IOException;
import main.java.duke.task.TaskList;
import main.java.duke.exception.DukeException;

/**
 * Represents the Duke object to start the program.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    /**
     * Creates a Duke object with an Ui object, storage object to the saved task list file in the
     * hard disk and a task list object being created after the saved task list file is saved.
     * If the directory to the saved task list file is not found, an IOException error will be raised
     * and caught.
     * @param fileName Name of the saved task list file.
     */
    public Duke(String fileName) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(fileName);
            this.tasklist = storage.formTaskList();
        } catch (IOException error) {
            System.out.println(error.toString());
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        try {
            Processor.process(tasklist, storage, ui);
        } catch (DukeException dukeException) {
            ui.printError(dukeException);
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
