package olivia.resource;

import olivia.logic.Storage;
import olivia.ui.Ui;

public class Wrapper {

    private boolean shouldExit = false;
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

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

    public void exit() {
        this.shouldExit = true;
    }

    public boolean isShouldExit() {
        return this.shouldExit;
    }

}
