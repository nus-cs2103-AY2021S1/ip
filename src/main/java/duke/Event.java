package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Event class to represent tasking that are classified as Events
 * @author Kor Ming Soon
 */
public class Event extends Task {

    private LocalDateTime durationFormatted;

    /**
     * Constructor for event class
     * @param task Details of the task.
     * @param duration Duration assigned to the task.
     */
    public Event (String task, String duration) {
        super(task, Tasktype.EVENT, duration);
        this.durationFormatted = LocalDateTime.parse(duration, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Overloaded constructor for the event class.
     * @param task Details of the task.
     * @param duration Duration assigned to the task.
     * @param isDone Completion status of the task.
     */
    public Event (String task, String duration, boolean isDone) {
        super(task, Tasktype.EVENT, duration, isDone);
        this.durationFormatted = LocalDateTime.parse(duration, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                durationFormatted.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")));
    }

}

