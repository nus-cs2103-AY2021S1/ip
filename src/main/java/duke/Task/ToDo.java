package duke.Task;

public class ToDo extends Task {

    /**
     * Constructs a <code>ToDo</code> Object to represent a todo
     *
     * @param description The description of a todo item
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String serialize() {
        return String.format("T | %d | %s", getStatusCode(), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
