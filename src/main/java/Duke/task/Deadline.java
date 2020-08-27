package Duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task. It has a description and a due timing attribute.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a new Deadline Task with a specified description and due time. String time
     * Object has to have proper formatting "yyyy-MM-dd HH:mm" as it will be parsed
     * as a LocalDateTime Object.
     *
     * @param description Describes the Deadline Task.
     * @param by The due timing of the Deadline Task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.storeAs = "D,0," + description + "," + by;
    }

    /**
     * Creates a Deadline Task with a specified done indicator, description and due time.
     * String time Object has to have proper formatting "yyyy-MM-dd HH:mm" as it will be parsed
     * as a LocalDateTime Object.
     *
     * @param done Indicates whether the task has been done.
     * @param description Describes the Deadline Task.
     * @param by The due timing of the Deadline Task.
     */
    public Deadline(String done, String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if (done.equals("1")) {
            this.isDone = true;
            this.storeAs = "D,1," + description + "," + by;
        }
        this.storeAs = "D,1," + description + "," + by;
    }

    /**
     * String representation of the Deadline Task
     *
     * @return representation of the Deadline Task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}