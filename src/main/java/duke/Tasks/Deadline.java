package duke.Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Initialises a Deadline task.
 */
public class Deadline extends Task {

    /** Stores the time of the task. */
    protected LocalTime localTime;

    /** Stores the date of the task. */
    protected LocalDate localDate;

    /**
     * Creates a deadline task.
     * @param name The description of the event.
     * @param localDate The date of the event.
     * @param localTime The time of the event.
     */
    public Deadline(String name, LocalDate localDate, LocalTime localTime) {
        super(name);
        this.localDate = localDate;
        this.localTime = localTime;
    }

    /**
     * Converts the task to a writable format for the data file.
     * @return The string representation of the task for the data file.
     */
    @Override
    public String toData(){
        return "D|" + super.toData() + localDate.toString() + " " + localTime.toString();
    }

    /**
     * Prints the string representation for the task for the user.
     * @return The string representation for the task for the user.
     */
    @Override
    public String toString() {
        String date = this.localDate.format(DateTimeFormatter.ofPattern("E MMM d yyyy"));
        String time = this.localTime.format(DateTimeFormatter.ofPattern("HH mm a"));
        return "[D]" + super.toString() + " (by: " + date + " " + time + ")";
    }
}
