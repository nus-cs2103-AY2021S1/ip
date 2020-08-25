package duke.task;

/**
 * This class represents a particular type of Task, corresponding to tasks of the "ToDo" form
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Gets a String which represents the ToDo in the appropriate format for storing to the hard disk
     * @return String in the storage format representing ToDo
     */
    @Override
    public String getStorageFormat() {
        return "T | " + super.getStorageFormat();
    }

    /**
     * Provides string representation of ToDo, used for UI display
     * @return String representation of ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
