package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class extends Task class to represent a task that happens at certain time.
 */
public class Event extends Task {
    String time;
    LocalDate localDate;

    /**
     * Constructor
     * @param name Name of the event.
     * @param status Completion status of the event.
     * @param time Time of the event.
     */
    public Event(String name, Status status, String time) {
        super(name, status);
        this.time = time;
        this.localDate = LocalDate.parse(time);

    }

    /**
     * String value to represent the object when printed for user.
     * @return String value that represents the Event object.
     */
    @Override public String toString() {
        return "[E] " + this.status.statusToSymbol() + this.name + " at: " +
                localDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

    }

    /**
     * Generates a String that represents the object to be stored in the data management file.
     * @return A String to be written into the text file.
     */
    @Override public String toStore() {
        return "[E] " + this.status.statusToSymbol() + this.name + " at: " + time;
    }

}
