package com.duke;

import com.duke.exceptions.DukeException;
import com.duke.storage.Storage;
import com.duke.tasklist.TaskList;
import com.duke.ui.Ui;

/**
 * Entry point to application.
 * Initializes all components needed to run program.
 */

public class Duke {
    private static String FILE_PATH = "src/main/data/input.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Duke Default Constructor. Includes filepath to persistent storage.
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

    /**
     * Duke main method. Initializes tasks from persistent storage file.
     */
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke(FILE_PATH);
        duke.ui.initialize(duke.tasks);
    }

}
