import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done before a specific time.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates Deadline object.
     * @param description Task description.
     * @param by Deadline of task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates Deadline object.
     * @param done Done state of task.
     * @param description Task description.
     * @param by Deadline of task.
     */
    public Deadline(String done, String description, LocalDate by) {
        super(description);
        this.by = by;
        this.isDone = done.equals("1");
    }
    
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
    
    @Override
    public String saveString() {
        return "D" + super.saveString() + " , " + this.by;
    }
}
