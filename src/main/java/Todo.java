/**
 * To-do is a type of Task, which has no specific due date/time.
 */
public class Todo extends Task {

    /**
     * Constructor that creates a new To-do.
     *
     * @param description describes the To-do.
     * @param id position of the To-do.
     */
    Todo(String description, int id) {
        super(description, id);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
