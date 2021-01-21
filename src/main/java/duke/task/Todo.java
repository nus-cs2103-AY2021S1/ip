package duke.task;

import java.util.Date;

/**
 * Represents a todo type of task.
 */
public class Todo extends Task {

    /**
     * Creates an instance of todo.
     * @param name the name of todo
     * @param isComplete the completion status of todo
     */
    public Todo(String name, boolean isComplete) {
        super(name, isComplete, TaskType.TODO);
    }

    /**
     * Represents the todo in a string.
     * @return the string representation of a todo
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    /**
     * Returns null since there is no date associated with todo
     * @return null
     */
    @Override
    public Date getDate() {
        return null;
    }
}
