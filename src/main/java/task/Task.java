package task;

import java.time.format.DateTimeFormatter;

/**
 * Abstract implementation of a task.
 */
public abstract class Task {

    protected DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
    protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private boolean completed;
    private String msg;

    /**
     * Creates new task object.
     *
     * @param msg Stored message of task.
     */
    public Task(String msg) {
        this.msg = msg;
        completed = false;
    }

    public String getDataString() {
        int completeInt = (completed) ? 1 : 0;
        return String.format("%d | %s", completeInt, msg);
    }

    public boolean contains(String query) {
        return toString().contains(query);
    }

    /**
     * Marks task as complete.
     */
    public void completeTask() {
        completed = true;
    }

    /**
     * Returns complete status of task.
     *
     * @return Complete status of task.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Returns stored message of task.
     *
     * @return Stored message of task.
     */
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "[" + (completed ? "\u2713" : "\u2717") + "] " + msg;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Task) {
            Task task = (Task) obj;
            return this.msg.equals(task.msg);
        }
        return false;
    }
}
