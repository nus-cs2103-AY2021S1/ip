package duke.storage;

import duke.task.TaskList;

/**
 * Represents the state of the Duke app at a point of time
 */
public class DukeState {

    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a new DukeState with the given TaskList and Storage
     *
     * @param taskList TaskList containing the Tasks in the app
     * @param storage Storage to retrieve and update the external storage
     */
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
