package duke.task;

import static duke.util.Keyword.TODO_SYMBOL;

import java.time.LocalDateTime;

/**
 * ToDo task, which is one of the three Task objects.
 */
public class ToDo extends Task {

    /**
     * Initializes the ToDo task with the description only. boolean isDone is false by default.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO, "-", LocalDateTime.now(), false);
    }

    public ToDo(String description, boolean isDone, String timeFrame, LocalDateTime dateTime) {
        super(description, TaskType.TODO, timeFrame, dateTime, isDone);
    }

    /**
     * Provides a string representation of the ToDo task.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return TODO_SYMBOL + super.toString();
    }
}
