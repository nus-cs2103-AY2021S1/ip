package duke.tasks;

import duke.DukeException;

/**
 * Represents a Task that can be marked as done.
 */
public class Task {
    private boolean isDone;
    private String taskName;

    /**
     * Constructs a Task.
     * @param name the displayed name of the Task.
     * @throws DukeException If the name is empty.
     */
    Task(String name) throws DukeException {

        if (name.trim().equals("")) {
            throw new DukeException("Charming, but we can't have nothing as a task.");
        }
        this.taskName = name.trim();
    }

    /**
     * Marks this Task as done.
     * @return false if Task is already marked as done before the call.
     */
    public boolean markDone() {
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }

    /**
     * Returns whether this Task has been marked as done.
     * @return true if the Task has been marked as done.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Returns this Task's name.
     * @return this Task's name.
     */
    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        assert taskName != null;

        String mark = isDone ? "\u2713" : "\u2717"; // u2713: unicode for tick, u2717: unicode for cross
        return "[" + mark + "] " + taskName;
    }
}
