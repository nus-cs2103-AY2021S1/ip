package duke;

import duke.task.TaskList;
import duke.utils.Storage;

/**
 * Encapsulate the main class of the object.
 */
public class Duke {
    /**
     * Executes the program.
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
