package duke.tasks;

import java.time.LocalDate;

/**
 * Class to define an Event.
 */
public class Event extends TimedTask {

    /**
     * Creates an Event with the given task name and time
     *
     * @param task Task name
     * @param ddl event time
     */
    public Event(String task, LocalDate ddl) {
        super(task, ddl);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", getDateTime());
    }

    /**
     * Returns string representation of the Event to store in file.
     *
     * @return string representation
     */
    @Override
    public String fileString() {
        return String.format("E|%s|%s", super.fileString(), ddl.toString());
    }
}
