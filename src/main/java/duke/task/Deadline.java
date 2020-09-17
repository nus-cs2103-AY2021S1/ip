package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Creates a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;
    
    /**
     * Creates a Deadline object.
     * 
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the message to be saved into the hard disk.
     * 
     * @return The string representation of the task in the local file.
     */
    @Override
    public String writeMessage() {
        String done = "";
        if (this.isDone) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("D | %s | %s | %s", done, this.description, this.deadline);
    }

    /**
     * Returns the string representation of this task to the users when Duke receives list command.
     * 
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
            String str = " (by: ";
            str += deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            return "[D][" + (this.isDone ? "✓" : "✗") + "] " + this.description + str + ")";
    }
}
