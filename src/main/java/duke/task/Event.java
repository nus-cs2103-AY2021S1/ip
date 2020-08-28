package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implements event objects.
 * 
 * @author Audrey Felicio Anwar
 */
public class Event extends Task {
    private LocalDate time;

    public Event(String description, boolean done, LocalDate time) {
        super(description, done);
        this.time = time;
    }

    /**
     * Describes event.
     *
     * @return String that describes event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Describes event to be saved in hard disk.
     *
     * @return String that will be stored on hard disk.
     */
    @Override
    public String saveToHardDisk() {
        return "E" + super.saveToHardDisk() + " | " + time;
    }
}
