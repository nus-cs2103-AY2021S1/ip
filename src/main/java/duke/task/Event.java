package duke.task;
import duke.parser.TimeParser;

/**
 * Represents a Event and consists of methods related to Event Task.
 */
public class Event extends Task {

    /**
     * Constructs a Event Task.
     *
     * @param eventName Event name.
     * @param eventTime Event time.
     */
    public Event(String eventName, String eventTime) {
        super(eventName, eventTime);
    }

    /**
     * Returns a string of the format required by the storage file.
     *
     * @return String describing the Event duke.task.
     */
    @Override
    public String writeToFile() {
        return "event" + "|" + this.getStatusSymbol() + "|"
                + this.taskName + "|" + this.time;
    }

    /**
     * Returns a string of a format to be printed by Duke.
     *
     * @return String describing the Event duke.task.
     */
    @Override
    public String toString() {
        TimeParser timeParser = new TimeParser(localDate, time);
        String formattedTime = timeParser.getFormattedTime();
        return "[E]" + super.toString() + " (at: " + formattedTime + ")";
    }
}
