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

    protected Event(String task, LocalDateTime date) {
        super(task);
        this.date = date;
    }

    /**
     * Creates a Event object
     * @param task Task to be added
     * @param date Date to be added
     * @return Event
     */
    public static Event createEvent(String task, LocalDateTime date) {
        return new Event(task, date);
    }

    /**
     * Returns a string representation of a Deadline Object
     * [E][X]** (task) at: (date) for tasks marked as Important
     * [E][X] (task) at: (date) for tasks not marked as Important
     * X for Undone Task, O for Done Task.
     * @return String
     */
    @Override
    public String toString() {
        String done = this.done ? "O" : "X";
        String date = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        if (isImportant) {
            return "[E][" + done + "]** " + this.task + "at: " + date;
        } else {
            return "[E][" + done + "] " + this.task + "at: " + date;
        }
    }
}
