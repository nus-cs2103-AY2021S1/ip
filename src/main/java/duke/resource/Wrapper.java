package duke.resource;

import duke.logic.Storage;
import duke.ui.Ui;

public class Wrapper {

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

}
