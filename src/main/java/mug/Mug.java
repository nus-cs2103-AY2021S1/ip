package mug;

import mug.storage.Storage;
import mug.tasks.TaskList;
import mug.ui.Ui;

/**
 * A class to get Mug response.
 */
public class Mug {
    /** user Interface Object*/
    private final Ui ui;
    /** A list of Tasks */
    private final TaskList tasks;
    /**
     * Constructs a Mug Object that create/read the file from the given filepath
     * and pass the information from the file to TaskList.
     */
    public Mug() {
        this.ui = new Ui();
        Storage storage = new Storage(Storage.MUG_FILE);
        storage.initialize();
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Returns respond from mug to user.
     * @param input User's input
     * @return Mug's responds.
     */
    public String getResponse(String input) {
        new Mug();
        return this.ui.readCommand(input, this.tasks);
    }
}
