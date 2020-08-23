package tasks;

/**
 * Tasks without any date/time attached to it.
 */
public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Overriden toString method for todo class
     * @return the string representation for todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
