package duke;

/**
 * Represents a task object.
 */
public class Task {

    private String name;
    private Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getTaskName() {
        return name;
    }

    public void setDone() {
        isDone = true;
    }

    /**
     * @return String that represents a tick or X icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getTaskName();
    }
}
