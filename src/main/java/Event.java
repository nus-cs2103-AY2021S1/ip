import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Creates an Event object.
     * 
     * @param task The description of the event.
     * @param at The time of the event.
     */
    public Event(String task, LocalDateTime at) {
        super(task);
        this.at = at;
    }

    /**
     * Returns the message to be saved in the hard disk.
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
        return String.format("E | %s | %s", done, this.task, this.at);
    }

    /**
     * Returns the string representation of the event to the users when Duke receives list command.
     * 
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String str = " (at: ";
        str += at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        return "[E][" + (this.done ? "✓" : "✗") + "] " + this.task + str + ")";
    }
}
