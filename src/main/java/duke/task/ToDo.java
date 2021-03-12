package duke.task;

import static duke.util.Keyword.NIL;
import static duke.util.Keyword.TODO_SYMBOL;

import java.time.LocalDateTime;

/**
 * ToDo task, which is one of the three Task objects. DateTime variable here refers to the time of creation of task.
 * Timeframe is also empty by default.
 */
public class ToDo extends Task {

    /**
     * Initializes the ToDo task with the description only. boolean isDone is false by default.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO, NIL, LocalDateTime.now(), false);
    }

    /**
     * Initializes the {@code ToDo} task with all parameters.
     * This is used when the Csv converter parses the task from the storage.
     *
     * @param description Description of task.
     * @param isDone Boolean representing whether task has been done.
     * @param timeFrame Time frame of ToDo task.
     * @param dateTime Date and time when task was created.
     */
    public ToDo(String description, boolean isDone, String timeFrame, LocalDateTime dateTime) {
        super(description, TaskType.TODO, timeFrame, dateTime, isDone);
    }

    /**
     * Provides a string representation of the {@code ToDo} task.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return TODO_SYMBOL + super.toString();
    }
}
