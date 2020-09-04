package duke.task;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * represents an event task
 */
public class Event extends Task {
    protected String eventTime;

    /**
     * creates a new event task based on the given description
     * @param taskDescription the full description of the event task in the following format:
     *                        "event event_task_description /by event_date"
     * @throws EmptyDescriptionException if the description given is empty
     * @throws EmptyDateException if the date given is empty
     */
    public Event(String taskDescription) throws EmptyDescriptionException, EmptyDateException {
        if (taskDescription.length() <= 6) {
            throw new EmptyDescriptionException("oops! the description of an event cannot be empty");
        } else if (!taskDescription.contains("/")) {
            throw new EmptyDateException("oops! the date for the event was not specified");
        } else {
            int space = taskDescription.indexOf(" ");
            int slash = taskDescription.indexOf("/");

            this.task = taskDescription.substring(space + 1, slash);
            this.eventTime = taskDescription.substring(slash + 4);
            this.done = false;
        }
    }

    /**
     * returns a string representation of the event task
     * @return string representation of the event task
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String eventTime;
        try {
            LocalDate localDate = LocalDate.parse(this.eventTime);
            eventTime = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            eventTime = this.eventTime;
        }

        sb.append("[E]")
                .append(super.toString())
                .append("(at: ").append(eventTime).append(")");
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
                this.eventTime;
    }

    /**
     * decodes a given line of text and transforms it into a event task
     * @param string the line of text to decode
     * @return the event task that has been decoded from the given input
     * @throws EmptyDescriptionException if the description given is empty
     * @throws EmptyDateException if the date given is empty
     */
    public static Event decode(String string) throws EmptyDescriptionException, EmptyDateException {
        String[] split = string.split(" \\| ");

        String taskDescription = "event " + split[2] + " /at " + split[3];

        Event event = new Event(taskDescription);

        if (split[1].contains("1")) {
            event.markAsDone();
        }

        return event;
    }
}
