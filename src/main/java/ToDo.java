/**
 * Represents the Todo Task.
 */
public class ToDo extends Task {
    /**
     * Constructor for Todo task object.
     * @param name The name of task to be done
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Overloaded Constructor for Todo task object.
     * @param name The name of task to be done
     * @param done The boolean for task completion status
     */
    public ToDo(String name, boolean done) {
        super(name, done);
    }

    /**
     * Setter method for setting done attribute to given input.
     * @return New ToDo task with same parameters but attribute done changed to given input
     */
    @Override
    public ToDo setDone() {
        return new ToDo(this.getName(), true);
    }

    /**
     * Updates the time of given Todo task.
     * @param time New updated time
     * @return Todo task with updated time
     */
    @Override
    public ToDo updateTime(String time) {
        return new ToDo(super.name, super.done);
    }

    /**
     * Returns the ToDo object string.
     * @return The ToDo object string name
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
