/**
 * Represents a task from user input.
 */
public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a task as done.
     * @return a String message informing the user of the completed task
     */
    public String markAsDone() {
        this.isDone = true;
        String message = "Nice! I've marked this task as done:";
        return String.format("%s%n  %s%n", message, this.toString());
    }

    public String getType() {
        return this.type;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTiming() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
