package duke;

/**
 * ToDo is a form of task and it contains description and status.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean status) {
        super(description, status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
