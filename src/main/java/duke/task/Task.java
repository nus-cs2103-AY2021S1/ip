package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates information that are shared by all tasks.
 */
public abstract class Task {

    protected boolean isDone = false;
    final String taskName;

    Task(String taskName) {
        this.taskName = taskName;
    }

    public void competeTask() {
        isDone = true;
    }

    public boolean isOnSameDay(LocalDate localDate) {
        return false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public abstract String generateStorageString();

    @Override
    public String toString() {
        if (isDone) {
            return "[\u2713] " + taskName;
        } else {
            return "[\u2718] " + taskName;
        }
    }
}
