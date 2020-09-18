package olivia.resource;

import olivia.logic.Storage;
import olivia.ui.Ui;

/**
 * Wrapper class that stores Olivia's Storage, TaskList and Ui objects,
 * as well as a boolean to check if ExitCommand has been called.
 */

public class Wrapper {

    private boolean shouldExit = false;
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor that creates a Wrapper object.
     * @param storage Olivia's Storage object; controls the logic behind
     *                writing and reading from text file.
     * @param taskList Olivia's TaskList object; manages the tasks in the
     *                 list.
     * @param ui Olivia's Ui object; manages the text to be returned to
     *           the GUI.
     */

    public Wrapper(Storage storage, TaskList taskList, Ui ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * Sets the shouldExit boolean to true to indicate that ExitCommand
     * has been called.
     */

    public void exit() {
        this.shouldExit = true;
    }

    public boolean isShouldExit() {
        return this.shouldExit;
    }

}
