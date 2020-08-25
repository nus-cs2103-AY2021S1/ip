package duke.task;

import java.util.Objects;

abstract public class Task {
    protected boolean isDone = false;
    String description;


    public Task(String description) {
        this.description = description;
    }

    public void markAsDone(){
        isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[✓] " : "[✗] ") + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isDone == task.isDone &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDone, description);
    }

    public boolean isDone() {
        return isDone;
    }

    abstract public String convertToData();
}
