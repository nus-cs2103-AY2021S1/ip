package duke;

import duke.task.TaskList;
import duke.ui.Ui;

public class Context {
    private final TaskList taskList;
    private final Ui ui;

    public Context(TaskList taskList, Ui ui) {
        assert taskList != null && ui != null : "taskList and ui should not be null";
        this.taskList = taskList;
        this.ui = ui;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Ui getUi() {
        return this.ui;
    }
}
