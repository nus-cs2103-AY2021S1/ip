package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class to represent tasking that are classified as deadline
 *
 * @author Kor Ming Soon
 */
public class Deadline extends Task {


    /**
     * Constructor for deadline task.
     *  @param task  Details of the task.
     * @param duration Duration assigned to the task.
     */
    public Deadline(String task, LocalDateTime duration) {
        super(task, TaskType.DEADLINE, duration);
    }

    /**
     * Overloaded constructor for the deadline class.
     *
     * @param task Details of the task.
     * @param duration Duration assigned to the task.
     * @param isDone Completion status of the task.
     */
    public Deadline(String task, LocalDateTime duration, boolean isDone) {
        super(task, TaskType.DEADLINE, duration, isDone);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)",
                super.toString(), getDuration().format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")));
    }

}
