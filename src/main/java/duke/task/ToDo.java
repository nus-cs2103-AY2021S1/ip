package duke.task;

/**
 * The ToDo class encapsulates information and methods pertaining to a ToDo.
 *
 * @author  Yen Pin Hsuan
 * @version 1.0
 */
public class ToDo extends Task {

    /**
     * Create a ToDo with the given details.
     * The ToDo is set as not done.
     * @param details Details of todo.
     */
    public ToDo(String details) {
        super(details);
    }

    /**
     * Create a ToDo with the given details and date.
     * The ToDo is set as done if isDone is true.
     * @param details Details of the todo.
     * @param isDone True if the todo is done.
     */
    public ToDo(String details, boolean isDone) {
        super(details, isDone);
    }

    /**
     * Return a string representation of the ToDo to be saved in hard disk.
     * @return String representation of the ToDo.
     */
    @Override
    public String store() {
        String done = this.isDone ? "T " : "F ";
        return "T " + done + this.details + "\n";
    }

    /**
     * Return a string representation of the ToDo to be printed.
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
