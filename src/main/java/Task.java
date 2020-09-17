import java.time.LocalDate;

/**
 * A class representing the general tasks valid for the Duke class.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String type;
    protected LocalDate time;

    public Task(String task, boolean isDone, LocalDate time) {

        description = task;
        this.isDone = isDone;
        type = null;
        this.time = time;

    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the task description.
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public LocalDate getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getStatus() {
        return isDone ? "1" : "0";
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "]" +
                " " + this.getDescription();
    }
}
