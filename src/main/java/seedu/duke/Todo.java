package seedu.duke;

/**
 * Create Todos that are a subtype of Task and stores the name of the Todo.
 */
public class Todo extends Task {

    /**
     * Initialize an instance of Todo.
     *
     * @param task Name of the Todo.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Initialize an instance of Todo.
     *
     * @param task Name of the Todo.
     * @param isDone Status of the Todo.
     */
    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * Provide a description of a Todo instance.
     *
     * @return String describing Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
