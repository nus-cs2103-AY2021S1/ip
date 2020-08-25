package duke.task;

import java.util.Objects;

public class Task {
    protected String description;
    protected taskStatus status;

    enum taskStatus {
        DONE, NOTDONE
    }

    public enum taskType {
        TODO, DEADLINE, EVENT
    }

    public Task(String desc, String type) {
        this.status = taskStatus.NOTDONE;
        this.description = desc;
    }

    public String getStatusIcon() {
        return (this.status == taskStatus.DONE
                ? "\u2713" // return tick
                : "\u2718");  // or X symbols
    }

    public void markAsDone() {
        this.status = taskStatus.DONE;
    }

    public boolean isDone() {
        return this.status == taskStatus.DONE;
    }

    public String toSaveFormat() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return description.equals(task.description) &&
                status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, status);
    }
}