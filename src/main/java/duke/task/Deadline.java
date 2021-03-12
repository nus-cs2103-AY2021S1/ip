package duke.task;

import static duke.util.Keyword.CLOSE_BRACKET;
import static duke.util.Keyword.DEADLINE_BY;
import static duke.util.Keyword.DEADLINE_SYMBOL;
import static duke.util.Keyword.NIL;

import java.time.LocalDateTime;

/**
 * Deadline class which has a unique deadline property.
 */
public class Deadline extends Task {

    /**
     * Initializes the {@code Deadline} task.
     *
     * @param description Description of task.
     * @param deadline Deadline of task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE, NIL, deadline, false);
    }

    /**
     * Initializes the {@code Deadline} task with all parameters.
     * This is used when the Csv converter parses the task from the storage.
     *
     * @param description Description of task.
     * @param isDone Boolean representing whether task has been completed.
     * @param timeFrame Time frame of task.
     * @param deadline Deadline of task.
     */
    public Deadline(String description, boolean isDone, String timeFrame, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE, timeFrame, deadline, isDone);
    }

    /**
     * Returns the {@code String} representation of this {@code Deadline} task.
     *
     * @return String representation of the {@code Deadline} task.
     */
    @Override
    public String toString() {
        return DEADLINE_SYMBOL + super.toString() + DEADLINE_BY + getTime() + CLOSE_BRACKET;
    }
}
