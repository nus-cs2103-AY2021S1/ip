/**
 * Class representing tasks to be done
 */
public class Todo extends Task {

    /**
     * Constructor of Todo class.
     *
     * @param description Todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor of Todo class.
     *
     * @param description Todo description.
     * @param isDone Describes if task is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns simple string format for file.
     *
     * @return Simple string description.
     */
    @Override
    public String toStringSimple() {
        return "T | " + super.toStringSimple();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
