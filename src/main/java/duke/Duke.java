package duke;

import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Storage;

/**
 * Encapsulate the main class of the object.
 */
public class Duke {
    /**
     * Executes the program.
     * Reads in a saved file if there is one and loads the data into the program.
     * Saves the data in the program and overwrite the save file when quitting.
     *
     * @param args command line arguments to be fed to the program.
     */
    public static void main(String[] args) {
        TaskList list = new TaskList();
        Ui ui = new Ui(list);
        Storage storage = new Storage(list);
        storage.readSavedFile();
        ui.run();
        storage.saveDataToFile();
    }
}
