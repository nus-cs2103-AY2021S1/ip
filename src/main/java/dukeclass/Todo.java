package dukeclass;

import java.time.LocalDateTime;

/**
 * A type of task.
 * The task has a String message and a Boolean status.
 */
public class Todo extends Task {

    private static final String ICON = "T";

    public Todo(String taskString) {
        super(taskString);
    }

    /**
     * Constructor for Todo task but with ability to set status
     *
     * @param taskString  the task given by the user
     * @param status  status of the task
     */
    public Todo(String taskString, boolean status) {
        super(taskString);
        this.status = status;
    }

    public Task snoozeTask() {
        return this;
    }

    public Task snoozeTask(int days) {
        return this;
    }

    @Override
    public String toString() {
        String statusIcon = (status) ? "\u2714" : "\u2716";
        return "[" + ICON + "]" + "[" + statusIcon + "] " + this.taskString;
    }

    @Override
    public String toDataString() {
        return "todo//" + taskString + "//" + status;
    }
}
