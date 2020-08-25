import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Class Constructor specifying the description of the Task.
     * @param description the description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the Task.
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the Status Icon of the Task.
     * @return the Task current Status Icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns the Type of the Task.
     * @return the type of Task.
     */
    public String getType() {
        if (type.equals("ToDos")) {
            return "T";
        } else if (type.equals("Deadlines")) {
            return "D";
        } else if (type.equals("Events")) {
            return "E";
        }
        return "Error";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "  [" + this.getType() + "][" + this.getStatusIcon() + "]" + this.description;
    }
}
