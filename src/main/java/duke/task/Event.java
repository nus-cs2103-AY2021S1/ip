package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h1> Event Task class </h1>
 *
 * The event class is a child class of Task
 * It contains an extra property: event that indicates
 * the date that a task is happening.
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class Event extends Task {

    protected LocalDateTime event;

    protected Event(String task, LocalDateTime event) {
        super(task);
        this.event = event;
    }

    private LocalDateTime getEvent() {
        return this.event;
    }

    public static Event createEvent(String task, LocalDateTime event) {
        return new Event(task, event);
    }

    @Override
    public String toString() {
        String done = this.done ? "O" : "X";
        String date = this.event.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E][" + done + "] " + this.task + "at: " + date;
    }
}
