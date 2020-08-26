import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline object.
     * 
     * @param task The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String task, LocalDateTime by) {
        super(task);
        this.by = by;
    }

    /**
     * Returns the message to be saved into the hard disk.
     * 
     * @return The string representation of the task in the local file.
     */
    @Override
    public String writeMessage() {
        String done = "";
        if (this.done) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("D | %s | %s | %s", done, this.task, this.by);
    }

    /**
     * Returns the string representation of this task to the users when Duke receives list command.
     * 
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
            String str = " (by: ";
            str += by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            return "[D][" + (this.done ? "✓" : "✗") + "] " + this.task + str + ")";
    }
}
