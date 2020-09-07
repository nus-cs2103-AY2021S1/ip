package duke;

/**
 * Wraps the tasklist in a Duke object.
 */
public class Duke {
    private TaskList taskList;

    public Duke() {
        this.taskList = new TaskList();
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
