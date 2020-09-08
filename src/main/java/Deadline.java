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
     * @return New Deadline task with same parameters but attribute done changed to given input
     */
    @Override
    public Deadline setDone() {
        return new Deadline(this.getName(), true, this.by);
    }

    /**
     * Updates the time of current deadline task.
     * @param time New updated time
     * @return Deadline task with updated time
     */
    @Override
    public Deadline updateTime(String time) {
        return new Deadline(super.name, super.done, time);
    }

    /**
     * Updates name of current Deadline Task.
     * @param name New update name
     * @return Deadline task with new name
     */
    @Override
    public Deadline updateName(String name) {
        return new Deadline(name, super.done, getBy());
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
