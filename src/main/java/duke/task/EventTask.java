package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an Event related DukeTask.
 * Apart from fields defined in <code>DukeTask</code>,
 * <code>EventTask</code> contains an additional <code>LocalDateTime</code>
 * to store information about the date and time of the Event.
 */
public class EventTask extends DukeTaskWithTime {

    public EventTask(String description, LocalDateTime datetime) {
        super(description, datetime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", getDatetime());
    }
}
