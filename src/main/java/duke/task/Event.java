package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task. It has a description and specified timing attribute.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Creates a new Event Task with a specified description and time attribute. String time
     * Object has to have proper formatting "yyyy-MM-dd HH:mm" as it will be parsed
     * as a LocalDateTime Object.
     *
     * @param description Describes the Event.
     * @param at The specified timing of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.storeAs = "E,0," + description + "," + at;
    }

    /**
     * Creates a new Event Task with a specified done indicator, description and time
     * attribute. String time Object has to have proper formatting "yyyy-MM-dd HH:mm"
     * as it will be parsed as a LocalDateTime Object.
     *
     * @param done  Indicates whether the task has been done.
     * @param description Describes the Event.
     * @param at The specified timing of the Event.
     */
    public Event(String done, String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if (done.equals("1")) {
            this.isDone = true;
            this.storeAs = "E,1," + description + "," + at;
        }
        this.storeAs = "E,1," + description + "," + at;
    }


    /**
     * String representation of the Event Task
     *
     * @return representation of the Event Task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
