package duke;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    // instance variables (constructor)
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // constructor
    public Duke() {
        this("data", "./data/duke.txt");
    }
    /**
     * Constructs a Duke object that represents
     * a particular Duke session
     *
     * @param directory the directory where the database is located
     * @param path path to the database file
     */
    public Duke(String directory, String path) {
        storage = new Storage(directory, path);
        tasks = new TaskList(storage.load());
        ui = new Ui();
    }

    // accessors for attributes
    public Storage getStorage() {
        return storage;
    }
    public TaskList getTaskList() {
        return tasks;
    }
    public Ui getUiComponent() {
        return ui;
    }

}
