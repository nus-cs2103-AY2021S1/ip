import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDate;

    /**
     * Creates a new Event Task with the input name and start time.
     *
     * @param in Task name
     * @param start Task timing
     */
    public Event(String in, LocalDateTime start) {
        super(in);
        this.startDate = start;
    }

    /**
     * Creates the string version of this Event for display.
     *
     * @return The string formatting of this Event.
     */
    public String toString() {
        String doneStatus;
        if (isDone) {
            doneStatus = "✓";
        } else {
            doneStatus = "✗";
        }
        return "[E] [" + doneStatus + "] " + task + " (at: " + dateToString(startDate) + ")";
    }

    /**
     * Creates the string version of this Event for the save file.
     *
     * @return The string formatting of this Event.
     */
    public String saveText() {
        return "E | " + super.saveText() + " | " + dateToSave(startDate);
    }
}
