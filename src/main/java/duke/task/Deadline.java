package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate time;

    public Deadline(String description, boolean done, LocalDate time) {
        super(description, done);
        this.time = time;
    }

    /**
     * Describes deadline.
     * 
     * @return String that describes deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Describes deadline to be saved in hard disk.
     * 
     * @return String that will be stored on hard disk.
     */
    @Override
    public String saveToHardDisk() {
        return "D" + super.saveToHardDisk() + " | " + this.time;
    }
}
