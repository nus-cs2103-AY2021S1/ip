package duke.task;

import java.util.Objects;

public class Task {
    protected final String description;
    protected taskStatus status;

    enum taskStatus {
        DONE, NOTDONE
    }

    public enum taskType {
        TODO, DEADLINE, EVENT
    }

    public Task(String description) {
        status = taskStatus.NOTDONE;
        this.description = description;
    }

    public String getStatusIcon() {
        return (status == taskStatus.DONE
            // return tick
            ? "\u2713"
            // or X symbols
            : "\u2718");
    }

    public void markAsDone() {
        status = taskStatus.DONE;
    }

    public boolean isDone() {
        return status == taskStatus.DONE;
    }

    public String toSaveFormat() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return description.equals(task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, status);
    }
}