/**
 * Encapsulates the todo object.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Converts to a string format to be saved in a text file.
     *
     * @return a string representation of the todo object.
     */
    @Override
    public String saveAsString() {
        return "T" + super.saveAsString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}