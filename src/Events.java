import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class that represents a Task that is an event. Extends from the Task class.
 */
public class Events extends Task {
    LocalDateTime time;

    /**
     * Overloaded constructor that creates an Event object that has description of the
     * task, the date and the time of the event, and whether the task has been completed.
     * @param description a String representing the description of the task.
     * @param time a LocalDateTime representing the date and time of the event.
     * @throws DateTimeParseException
     */
    public Events(String description, String time) throws DateTimeParseException {
        super(description);
        this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Overloaded constructor that creates an Event object that has description of the
     * task, the date and the time of the event, and whether the task has been completed.
     * @param description a String representing the description of the task.
     * @param time a LocalDateTime representing the date and time of the event.
     * @param isDone a boolean representing whether the task has been completed.
     */
    public Events(String description, String time, Boolean isDone) {
        super(description, isDone);
        this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + this.getIcon() + description + " (at: " +
                this.time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) + ")";
    }

    @Override
    public String toSaveString() {
        return String.format("E | %s | %s | %s",
                super.doneString(), this.description, this.time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }


}
