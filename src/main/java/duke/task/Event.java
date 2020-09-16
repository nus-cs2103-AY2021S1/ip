package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Event task that takes in a description and a date.
 */
public class Event extends Task {

    private LocalDate date;

    /**
     * Constructor for Event Task object.
     *
     * @param task The description of the task.
     * @param date The LocalDate object that specifies the due date.
     */
    public Event(String task, LocalDate date) {
        super(task);
        this.date = date;
    }

    /**
     * Constructor for creating a Event Task object
     * however allowing the done property to be written.
     *
     * @param task The description of the task.
     * @param done Where the task is done yet.
     * @param date The LocalDate object that specifies the due date.
     */
    public Event(String task, boolean done, LocalDate date) {
        super(task, done);
        this.date = date;
    }

    /**
     * Constructor for creating a Event Task object
     * however allowing the done property and note to be written.
     *
     * @param task The description of the task.
     * @param done Where the task is done yet.
     * @param date The LocalDate object that specifies the due date.
     * @param note The note to be written.
     */
    public Event(String task, boolean done, LocalDate date, String note) {
        super(task, done, note);
        this.date = date;
    }

    @Override
    public String getSaveString() {
        return "[D] " + super.getSaveString() + " /at " + this.date;
    }
    @Override
    public String toString() {
        String dateString = "No date set";
        if (this.date != null) {
            DateTimeFormatter formatters = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
            dateString = this.date.format(formatters);
        }

        return "[Event] " + super.toString() + " (at: " + dateString + ")";
    }
}
