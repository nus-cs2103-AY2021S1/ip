package duke;

/**
 * ToDo is a subclass of Task.
 */
public class ToDo extends Task {
    // ToDos: Tasks without any date/time attached to it
    // Example: Visit new theme park

    /**
     * Constructor of ToDo object.
     * 
     * @param description Takes in the description of the ToDo object.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the task type of ToDo.
     *
     * @return the task type of ToDo.
     */
    @Override
    public String getTaskType() {
        return "ToDo";
    }

    /**
     * Formats the string of a ToDo object.
     *
     * @return a formatted string for a ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the string of a ToDo object to be stored into hard drive.
     *
     * @return a formatted string suitable for storage in hard drive for a ToDo object.
     */
    @Override
    public String toStringInFile() {
        return "T" + super.toStringInFile();
    }
}
