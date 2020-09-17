package duke;

/**
 * Todo is a subclass of Task that handles all the todo event added by user.
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean done) {
        super(description, done);
    }

    @Override
    public String inputStyle() {
        return "todo " + super.inputStyle() + " #" + super.priority;
    }
    @Override
    public String toString() {
        if (super.priority == Priority.HIGH) {
            return "#" + super.priority.toString() + " [T]" + super.toString();
        } else {
            return "[T]" + super.toString();
        }
    }
}
