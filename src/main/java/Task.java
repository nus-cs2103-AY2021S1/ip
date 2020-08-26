/**
 * Encapsulates a backbone of a Task
 * Every Task has a String to store its description
 * Every Task has a boolean to denote if it is done
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     * Used when a Task is created for the first time
     *
     * @param description is the Customer associated with this event
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor
     * Used to create a Task with the option to mark its done state
     *
     * @param description is the Customer associated with this event
     * @param isDone is a boolean that denotes if a Task is done or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Returns the description
     *
     * @return a String
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Shows if a Task is done or not
     *
     * @return a String
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a Task as done
     */
    protected void finish() {
        this.isDone = true;
    }

    /**
     * Returns the presentation of a Task
     *
     * @return a String.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}