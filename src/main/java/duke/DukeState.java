package duke;

import duke.task.TaskList;

public class DukeState {

    private TaskList taskList;
    private Storage storage;

    public DukeState(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }
}
