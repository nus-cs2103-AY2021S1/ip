package duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents event which is a modified form of task.
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * Constructs an event with the description of the event and the date of the event.
     * @param description description of the event.
     * @param date date of the event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructs an event with the description of the event, date of the event and status of completion.
     * @param descriptiondescription of the event.
     * @param date date of the event.
     * @param isDone boolean value of whether it is completed.
     */
    public Event(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns the type of task, which is event.
     * @return type of task, which is event.
     */
    @Override
    public String getTypeOfTask() {
        return "event";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd E");
        String dateText = this.date.format(formatter);
        return "[E] [" + this.getStatusIcon() + "] " + this.description + " ----- When: " + dateText;
    }

    /**
     * Returns the representation of event to be stored in hard disk.
     * @return String representation of the event for storage.
     */
    @Override
    public String getStoreRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dateText = this.date.format(formatter);
        String doneStatus = this.isDone ? "D," : "N,";
        return "E," + doneStatus + this.description + "," + dateText;

    }
}
