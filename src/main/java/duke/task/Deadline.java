package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implements deadline objects.
 *
 * @author Audrey Felicio Anwar
 */
public class Deadline extends Task {
    private LocalDate time;

    /**
     * Initializes a Deadline object.
     *
     * @param description The task description.
     * @param done Indicates whether the task is done.
     * @param time Indicates the deadline of task.
     */
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
        return "[D]" + super.toString() + " (by: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Describes deadline to be saved in hard disk.
     *
     * @return String that will be stored on hard disk.
     */
    @Override
    public String saveToHardDisk() {
        return "D" + super.saveToHardDisk() + " | " + time;
    }
}
