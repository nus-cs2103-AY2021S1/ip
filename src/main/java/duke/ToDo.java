package duke;

/**
 * The ToDo class contains the name and completion status of the task.
 */
public class ToDo extends Task {

    /**
     * Constructor of a ToDo object.
     * 
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of a ToDo object to be stored in the storage.
     * 
     * @return A String representing the code of the task stored in the storage.
     */
    @Override
    public String encode() {
        return String.format("T | %s | %s", isDone ? 1 : 0, description);
    }

    /**
     * Returns a string representation of a ToDo object.
     * 
     * @return A String containing the description and completion status of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
