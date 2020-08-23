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
     * Overloaded Constructor for Todo tasl object.
     * @param name The name of task to be done
     * @param done The boolean for task completion status
     */
    public ToDo(String name, boolean done) {
       super(name, done);
    }

    /**
     * Setter method for setting done attribute to given input.
     * @param b The new completion status
     * @return New ToDo task with same parameters but attribute done changed to given input
     */
    @Override
    public ToDo setDone(boolean b) {
        return new ToDo(this.getName(), true);
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
