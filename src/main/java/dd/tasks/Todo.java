package dd.tasks;

/**
 * A to-do task.
 */
public class Todo extends Task {

    /**
     * Class Constructor.
     *
     * @param description Description of to-do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveString() {
        if (this.isDone) {
            return "T , 1 , " + description;
        } else {
            return "T , 0 , " + description;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
