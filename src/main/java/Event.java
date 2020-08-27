import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task that has a specific start and end date and time.
 * An Event object is made up of the task name, as well as a date and time of the event.
 */
public class Event extends Task {
    protected LocalDateTime at;
    private String originalFormat;
    private String printedFormat;

    /**
     * Instantiates a Event object if the input was written in the correct format.
     * @param taskName The description of the task.
     * @param date The date and time of the event.
     * @throws DukeException If the event is not specified in the correct format.
     */
    public Event(String taskName, String date) throws DukeException {
        super(taskName);
        try {
            this.at = LocalDateTime.parse(
                    date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.originalFormat = this.at.format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.printedFormat = this.at.format(
                    DateTimeFormatter.ofPattern("EEE, d MMM yyyy, HH:mm"));
        } catch (DateTimeParseException e) {
            Task.totalTasks--;
            throw new DukeException("You need to use the proper format!\n"
                    + "eg event project meeting /at 2019-10-15 1200");
        }
    }

    /**
     * Returns the string representation of the event task in the format to be saved in the computer.
     * @return String representation of the task.
     */
    @Override
    public String toTaskData() {
        return "E" + " ; " + super.toTaskData() + " ; " + originalFormat;
    }

    /**
     * Returns the string representation of the event task.
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + printedFormat + ")";
    }
}
