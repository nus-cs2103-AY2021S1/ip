package duke.task;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */

public class Event extends Task {
    private final DukeDateTime dateTime;

    public Event(String content, DukeDateTime dateTime) {
        super(content);
        this.dateTime = dateTime;
    }

    public Event(String content, DukeDateTime dateTime, boolean isDone) {
        super(content, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String toDataFileFormat() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, content, dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
