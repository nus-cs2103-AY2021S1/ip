package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Event class to represent tasking that are classified as Events
 * @author Kor Ming Soon
 */
public class Event extends Task {

    /**
     * Constructor for event class
     *  @param task Details of the task.
     * @param duration Duration assigned to the task.
     */
    public Event (String task, LocalDateTime duration) {
        super(task, TaskType.EVENT, duration);
    }

    /**
     * Overloaded constructor for the event class.
     *
     * @param task Details of the task.
     * @param duration Duration assigned to the task.
     * @param isDone Completion status of the task.
     */
    public Event (String task, LocalDateTime duration, boolean isDone) {
        super(task, TaskType.EVENT, duration, isDone);
    }


    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                getDuration().format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")));
    }

}

