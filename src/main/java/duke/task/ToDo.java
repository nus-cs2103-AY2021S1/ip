package duke.task;

import java.time.LocalDate;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * Gets the task type.
     *
     * @return type of task in String.
     */
    @Override
    public String getTaskType() {
        return "todo";
    }

    @Override
    LocalDate getDate() {
        return null;
    }

    /**
     * Returns a String representation of the task to be stored in the
     * storage file.
     *
     * @return Formatted String representing the task.
     */
    @Override
    public String toDataString() {
        return "T // " + (isDone ? "1" : "0") + " // " + task;
    }

    /**
     * Returns a String representation of the todo for display.
     *
     * @return String representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
