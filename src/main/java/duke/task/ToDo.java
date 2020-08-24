package duke.task;

/**
 * Type of Task that is to be completed by the user.
 */
public class ToDo extends Task {

    /**
     * Constructor to create a <code>ToDo</code> Task object.
     *
     * @param description the details of the ToDo Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overloaded constructor used when Tasks are being loaded from the user's local storage.
     *
     * @param description the details of the ToDo Task.
     * @param isDone whether the Task is completed or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Prints out the details of the ToDo Task, as well as whether or not it is completed.
     *
     * @return a <code>String</code> representation of the ToDo Task.
     */
    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
