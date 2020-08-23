package Tasks;

/**
 * Create Todo objects extends from Task class.
 */
public class Todo extends Task {

    /**
     * Constructs Todo object with Todo's description.
     *
     * @param description Todo's description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs Todo object with Todo's description and Todo's status.
     *
     * @param description Todo's describtion
     * @param isDone Todo's status
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Create a new Todo object with isDone equal true.
     *
     * @return New Todo object.
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
