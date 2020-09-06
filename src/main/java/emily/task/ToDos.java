package emily.task;

/**
 * Represents a task that has a simple description
 */
public class ToDos extends Task {

    /**
     * Subclass of Task with a given type
     *
     * @param description time provided
     */
    public ToDos(String description) {
        super(description);
        this.type = 'T';
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
