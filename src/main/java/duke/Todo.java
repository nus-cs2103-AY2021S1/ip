package duke;

/**
 * Implements a ToDo Task
 */
public class Todo extends Task {

    /**
     * Constructor
     * @param description Title of the Todo Task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * String representation of Todo object
     * @return String representation of Todo object
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * String representation of Todo object to be saved to hard disk
     * @return String representation of Todo object to be saved to hard disk
     */
    public String fileFormat() {
        return "T , " + super.fileFormat();
    }
}