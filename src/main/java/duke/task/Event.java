package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * represents an event task
 */
public class Event extends Task {
    protected String eventDate;

    /**
     * creates a new event task based on the given description
     * @param taskDescription the full description of the event task in the following format:
     *                        "event event_task_description /by event_date"
     */
    public Event(String taskDescription) {
        int slash = taskDescription.indexOf("/");
        this.task = taskDescription.substring(0, slash);
        this.eventDate = taskDescription.substring(slash + 4);
        this.done = false;
    }

    /**
     * returns a string representation of the event task
     * @return string representation of the event task
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String eventDate;
        try {
            LocalDate localDate = LocalDate.parse(this.eventDate);
            eventDate = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            eventDate = this.eventDate;
        }

        sb.append("[E]")
                .append(super.toString())
                .append("(at: ").append(eventDate).append(")");
        return sb.toString();
    }

    /**
     * encodes the event task to a more appropriate format for storage
     * @return the encoded version of the event task
     */
    @Override
    public String encode() {
        return "E | " +
                this.isDoneInt() + " | " +
                this.task + "| " +
                this.eventDate;
    }

    /**
     * decodes a given line of text and transforms it into a event task
     * @param string the line of text to decode
     * @return the event task that has been decoded from the given input
     */
    public static Event decode(String string) {
        String[] split = string.split(" \\| ");

        String taskDescription = split[2] + " /at " + split[3];

        Event event = new Event(taskDescription);

        if (split[1].contains("1")) {
            event.markAsDone();
        }

        return event;
    }
}
