package main.java.farrell.duke;

/**
 * Encapsulates data for a todo task.
 */
public class ToDo extends Task {

    ToDo(String description) throws DukeException {
        this(description, false);
    }

    ToDo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
        taskType = TaskType.TODO;
        if (description == null || description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
