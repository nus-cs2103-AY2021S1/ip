package duke.tasks;

import duke.DukeException;

public class Task {
    private boolean isDone;
    private String taskName;

    Task(String name) throws DukeException {

        if (name.trim().equals("")) {
            throw new DukeException("Charming, but we can't have nothing as a task.");
        }
        this.taskName = name.trim();
    }

    // returns false if task is already done
    public boolean markDone() {
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }

    public boolean getDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        String mark = isDone ? "✓" : "✗";
        return "[" + mark + "] " + taskName;
    }
}
