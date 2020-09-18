/**
 * Represents a to-do task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the format for permanent storage of a to-do task in file.
     * @return string format for storing.
     */
    @Override
    public String getStoringFormat() {
        return "T " + super.getStoringFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
