/**
 * Represents a "to do" task with a description.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    protected String toData() {
        return "T | " + super.toData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
