package duke.tasks;

/**
 * The type Todo.
 */
public class Todo extends Task {
    /**
     * Instantiates a new Todo.
     *
     * @param description the description
     * @param done        the done
     */
    public Todo(String description, String done) {
        super(description, done);
    }

    @Override
    public String[] getStringArr() {
        String[] arr = {"T", super.done, super.description};
        return arr;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
