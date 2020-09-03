/**
 * Wrapper class for Todo tasks.
 */
package sg.christopher.duke.entities;

public class Todo extends Task {
    /**
     * Creates a new Todo task.
     *
     * @param description description of Todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
