package duke;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected boolean recurring;

    /**
     * Class Constructor specifying the description of the Task.
     * @param description the description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.recurring = false;
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

    /**
     * Returns a string to indicate that a task is recurring.
     * @return the string to indicate recurrence
     */
    public String recurringPrinter() {
        if (this.recurring) {
            return " [Recurring Weekly]";
        } else {
            return "";
        }
    }

    public void removeRecurring() {
        this.description = this.description.replaceAll("recurring" , "");
    }

    public String toString() {
        return "  [" + this.getType() + "][" + this.getStatusIcon() + "]" + this.description + recurringPrinter();
    }
}
