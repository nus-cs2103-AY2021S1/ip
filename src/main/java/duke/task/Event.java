package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a <code>Task</code> that takes place on a certain date.
 */
public class Event extends Task {
    private LocalDate duration;

    /**
     * Class constructor.
     *
     * @param title    the content of the <code>Event</code>
     * @param duration the date on which the <code>Event</code> takes place
     */
    public Event(String title, LocalDate duration) {
        super(title);
        this.duration = duration;
    }

    /**
     * Class constructor.
     *
     * @param title    the content of the <code>Event</code>
     * @param isDone   whether or not the <code>Event</code> is marked as completed
     * @param duration the date on which the <code>Event</code> takes place
     */
    public Event(String title, boolean isDone, LocalDate duration) {
        super(title, isDone);
        this.duration = duration;
    }

    @Override
    public String toString() {
        String date = this.duration.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

    @Override
    public String print() {
        return "E | " + super.print() + " | " + this.duration;
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return this.duration.equals(date);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Event) {
            Event otherTask = (Event) obj;
            return super.equals(otherTask) && this.duration.equals(otherTask.duration);
        } else {
            return false;
        }
    }

    @Override
    public boolean isDuplicate(Task task) {
        if (task == this) {
            return true;
        } else if (task instanceof Event) {
            Event otherTask = (Event) task;
            return super.isDuplicate(otherTask);
        } else {
            return false;
        }
    }
}
