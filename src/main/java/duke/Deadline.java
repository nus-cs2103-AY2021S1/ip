package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    /**
     * Returns a deadline. This is a constructor of deadline.
     * @param description describes the deadline task
     * @param by the deadline of the task
     *
     * @return a deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string that represents the Deadline task with deadline in the format of MMM dd yyyy.
     *
     * @return String of Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
