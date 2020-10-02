package shiro.task;

import shiro.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * represents an event task
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * creates a new event task based on the given description
     * @param taskDescription the full description of the event task in the following format:
     *                        "event event_task_description /by event_date"
     */
    public Event(String taskDescription) {
        int slash = taskDescription.indexOf("/");
        this.task = taskDescription.substring(0, slash);
        this.date = Parser.parseDate(taskDescription.substring(slash + 4));
        this.done = false;
    }

    /**
     * returns a string representation of the event task
     * @return string representation of the event task
     */
    public String toString() {
        return "[E]" + super.toString() +
                "(at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
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
                this.date;
    }

    /**
     * returns the date of the event task as a LocalDate object
     * @return date of the event task
     */
    @Override
    public LocalDate getDate() {
        return this.date;
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
