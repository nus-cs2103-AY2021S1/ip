package Tasks;

/**
 * Create Todo objects extends from Task class.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Create a new Todo object with isDone equal true.
     * @return new Todo object.
     */
    @Override
    protected Todo markAsDone() {
        return new Todo(super.description, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
