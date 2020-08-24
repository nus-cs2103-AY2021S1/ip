package duke.task;

/**
 * Encapsulates the todo class
 */
public class ToDo extends Task{

    /**
     * Creates a new todo
     * @param desc Description given
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns the string representation
     * @return String representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string to be saved
     * @return String to be saved
     */
    @Override
    public String toFileString() {
        return "T\n" + super.toFileString();
    }
}
