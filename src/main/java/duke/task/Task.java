package duke.task;

import java.util.Objects;

/**
 * Represents a task. Task will generally have a description and have a isDone state. Some subclasses
 * will have other details such as time (e.g. Event, Deadline)
 */
abstract public class Task {
    protected boolean isDone = false;
    String description;


    public Task(String description) {
        this.description = description;
    }

    /**
     * Mark a task as done.
     */
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

    /**
     * Returns a string that can be used to write to memory.
     *
     * @return  String that will be used to write to memory.
     */
    abstract public String convertToData();
}
