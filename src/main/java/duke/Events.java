package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Events class will handle tasks categorised as event.
 */
public class Events extends Task {

    /** Scheduled event date in LocalDate format */
    protected LocalDate scheduled;
    /** Scheduled event date in String format */
    protected String date;

    /**
     * Initialises Events using description and scheduled event date.
     *
     * @param description
     * @param scheduled
     */
    public Events(String description, String scheduled) {
        super(description);
        this.date = scheduled;
        this.scheduled = LocalDate.parse(scheduled);
    }

    /**
     * Initialises Events using description, scheduled event date and isDone.
     * Used when knowledge about isDone is needed, eg. loading existing list from hard disk.
     *
     * @param description
     * @param scheduled
     * @param isDone
     */
    public Events(String description, String scheduled, boolean isDone) {
        super(description, isDone);
        this.date = scheduled;
        this.scheduled = LocalDate.parse(scheduled);
    }

    /**
     * Returns string format of event.
     *
     * @return String description of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + scheduled.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns string format of event that will be written on a text file.
     *
     * @return String description of event
     */
    @Override
    public String writeToFile() { return "E" + super.writeToFile() + " | " + date; }
}