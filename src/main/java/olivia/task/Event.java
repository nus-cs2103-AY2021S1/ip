package olivia.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class that represents a Task that is an event. Extends from the Task class.
 */

public class Event extends DatedTask {

    /**
     * Constructor that creates an Event object that has a description of the
     * task, the date and time of the event, and whether the task has been
     * completed.
     *
     * @param description a String representing the description of the task
     * @param time a LocalDateTime representing the date and time of the event
     * @param isDone a boolean representing whether the task has been completed
     * @throws DateTimeParseException exception thrown if input date was not
     *                                correctly formatted
     */

    public Event(String description, String time, boolean isDone)
            throws DateTimeParseException {
        super(description, "E", time, isDone);
    }

    /**
     * Returns a String formatted to how an Event should be saved in the associated
     * save file.
     *
     * @return a formatted String to be written to the save file
     */

    @Override
    public String toSave() {
        return String.format("%s | %s", super.toSave(),
                this.time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }

}
