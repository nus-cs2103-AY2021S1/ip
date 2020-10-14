import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a new Deadline Task with the input name and deadline.
     *
     * @param in Task name
     * @param deadline Task deadline
     */
    public Deadline(String in, LocalDateTime deadline) {
        super(in);
        this.deadline = deadline;
    }

    /**
     * Creates the string version of this Deadline for display.
     *
     * @return The string formatting of this Deadline.
     */
    public String toString() {
        String doneStatus;
        if (isDone) {
            doneStatus = "✓";
        } else {
            doneStatus = "✗";
        }
        return "[D] [" + doneStatus + "] " + task + " (by: " + dateToString(deadline) + ")";
    }

    /**
     * Creates the string version of this Deadline for the save file.
     *
     * @return The string formatting of this Deadline.
     */
    public String saveText() {
        return "D | " + super.saveText() + " | " + dateToSave(deadline);
    }
}
