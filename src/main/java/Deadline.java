/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructor for Deadline task object.
     * @param name The name of task to be done
     * @param by The due date of the task
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Overloaded constructor for Deadline task object.
     * @param name The name of task to be done
     * @param done The boolean for task completion status
     * @param by The due date of the task
     */
    public Deadline(String name, boolean done, String by) {
        super(name, done);
        this.by = by;
    }

    /**
     * Getter method for attribute by.
     * @return The attribute by of Deadline object
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Setter method for setting done attribute to given input.
     * @param b The new completion status
     * @return New Deadline task with same parameters but attribute done changed to given input
     */
    @Override
    public Deadline setDone(boolean b) {
        return new Deadline(this.getName(),true, this.by);
    }

    /**
     * Returns the Deadline object string.
     * @return The Deadline object string name
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
