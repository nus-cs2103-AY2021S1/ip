package duke;

import duke.task.TaskList;

/**
 * Represents the state of the Duke app at a point of time
 */
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
