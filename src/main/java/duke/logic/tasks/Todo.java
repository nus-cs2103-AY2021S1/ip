package duke.logic.tasks;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidUpdateTodoException;

/**
 * Represents a todo task without any date/time attached to it.
 * A Todo object only consists of the task name.
 */
public class Todo extends Task {
    /**
     * Instantiates a Todo object.
     *
     * @param taskName The description of the task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public void setTaskDateTime(String dateTime) throws DukeException {
        throw new InvalidUpdateTodoException();
    }

    /**
     * Returns the string representation of the todo task in the format to be saved in the computer.
     *
     * @return String representation of the task.
     */
    @Override
    public String toTaskData() {
        String separator = " ; ";
        return "T" + separator + super.toTaskData();
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
