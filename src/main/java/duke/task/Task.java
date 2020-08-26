package duke.task;

import java.time.LocalDate;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract boolean isDue(LocalDate date);

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toSaveData() {
        return (isDone ? 1 : 0) + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
