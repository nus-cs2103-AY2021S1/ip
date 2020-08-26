package duke.Tasks;

/**
 * Initialises a toDo task.
 */
public class ToDo extends Task {

    /**
     * Creates a todo task with a description.
     * @param name The description of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Converts the task to a writable format for the data file.
     * @return The string representation of the task for the data file.
     */
    @Override
    public String toData(){
        return "T///" + super.toData();
    }

    /**
     * Prints the string representation for the task for the user.
     * @return The string representation for the task for the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
