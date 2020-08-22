/**
 * To Do class.
 * Represents a to do task.
 * Extends from Task.
 *
 * @author YanCheng
 */

public class ToDo extends Task {

    /**
     * Constructor for To Do.
     * @param toDoName name of deadline
     */
    public ToDo(String toDoName) {
        super(toDoName);
    }

    /**
     * Return to do information to be stored locally.
     * @return to do information
     */
    @Override
    public String getInfo() {
        return String.format("T | %d | %s\n", isDone ? 1 : 0, taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
