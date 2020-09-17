package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task with a simple description.
 */
public class ToDo extends Task {

    /**
     * Class constructor.
     *
     * @param description A string representing the task description.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Class constructor specifying whether or not the task has been completed.
     *
     * @param description A string representing the task description.
     * @param isDone      <code>true</code> if the task has been completed;
     *                    <code>false</code> otherwise.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    @Override
    public String getTimeString() {
        throw new IllegalStateException("Impossible method call");
    }

    @Override
    public String printTime() {
        throw new IllegalStateException("Impossible method call");
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.EPOCH;
    }

    @Override
    public LocalTime getTime() {
        return LocalTime.MIN;
    }

}
