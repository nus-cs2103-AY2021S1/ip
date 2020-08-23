/**
 * Class representing a Todo
 */

public class Todo extends Task {

    /**
     * Constructor for Todo
     * @param description description of the Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints the Todo object
     * @return string representation of a Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
