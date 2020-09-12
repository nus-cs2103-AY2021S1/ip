package main.java.farrell.duke.task;

import main.java.farrell.duke.DukeException;

/**
 * Encapsulates data for a todo task.
 */
public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        this(description, false);
    }

    /**
     * Creates a ToDo with a description and completion status.
     *
     * @param description
     * @param isDone
     * @throws DukeException
     */
    public ToDo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
        taskType = TaskType.TODO;
        if (description == null || description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty!");
        }
    }

    /**
     * Creates a ToDo from some formatted saved data.
     *
     * @param data The data to convert.
     * @return The resulting ToDo object.
     * @throws DukeException If the saved data is invalid.
     */
    public static ToDo fromData(String[] data) throws DukeException {
        try {
            return new ToDo(data[2], Boolean.parseBoolean(data[1]));
        } catch (Exception exception) {
            throw new DukeException("Saved data is invalid!");
        }
    }

    @Override
    public String convertToData() {
        return taskType.name() + "|"
                + (isDone ? "true" : "false") + "|"
                + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
