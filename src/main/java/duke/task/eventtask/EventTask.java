package duke.task.eventtask;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of task that deals with events.
 */
public class EventTask extends Task {
    private final LocalDateTime eventDate;

    /**
     * Constructor for the event task object.
     * @param name name of the event task
     * @param date Date when the entry for this event task is created
     * @param eventDate Date indicated by the user on when the event will happen.
     */
    public EventTask(String name, LocalDateTime date, String eventDate) {
        super(name, date);
        this.eventDate = LocalDateTime.parse(eventDate, formatter);
    }

    /**
     * Overloaded constructor when the event task object is re-created from a tasklist.txt file.
     * @param line input from the tasklist.txt file
     */
    public EventTask(String line) {
        super(line);
        this.eventDate = LocalDateTime.parse(line.substring(line.indexOf("(at: ") + 5,line.lastIndexOf(")")),
                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }


    private String getEventDate() {
        return eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.getEventDate() + ")";
    }
}
