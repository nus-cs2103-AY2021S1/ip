package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class to represent tasking that are classified as deadline
 * @author Kor Ming Soon
 */
public class Deadline extends Task {

    private LocalDateTime durationFormatted;

    /**
     * Constructor for deadline task.
     * @param task  Details of the task.
     * @param duration Duration assigned to the task.
     */
    public Deadline(String task, String duration) {
        super(task, Tasktype.DEADLINE, duration);
    }

    /**
     * Overloaded constructor for the deadline class.
     * @param task Details of the task.
     * @param duration Duration assigned to the task.
     * @param isDone Completion status of the task.
     */
    public Deadline(String task, String duration, boolean isDone) {
        super(task, Tasktype.EVENT, duration, isDone);
        this.durationFormatted = LocalDateTime.parse(duration, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)",
                super.toString(), durationFormatted.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")));
    }

}
