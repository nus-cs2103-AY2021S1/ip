package duke;

/**
 * Runs the chatbot.
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
