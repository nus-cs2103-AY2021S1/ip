package duke;

import java.io.Serializable;
import java.util.Objects;

public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "X"); //return tick or X symbols
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone &&
            Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }
}
