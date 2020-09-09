package stub;

import java.time.LocalDateTime;

import duke.task.DukeTaskWithTime;

/**
 * Represents a DukeTaskWithTime (Deadline or Event task)
 * Used for testing
 */
public class DukeTaskWithTimeStub extends DukeTaskWithTime {
    public DukeTaskWithTimeStub(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }
}
