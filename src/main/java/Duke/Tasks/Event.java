package duke.tasks;

import java.time.LocalDateTime;

import duke.main.Parser;

/**
 * The type Event.
 */
public class Event extends Task {

    /**
     * The At.
     */
    protected LocalDateTime at;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param done        the done
     * @param at          the at
     */
    public Event(String description, String done, LocalDateTime at) {
        super(description, done);
        this.at = at;
    }

    @Override
    public String[] getStringArr() {
        String[] stringArr = this.at.toString().split("T");
        String[] timeArr = stringArr[1].split(":");
        String t = String.format("%s %s%s", stringArr[0], timeArr[0], timeArr[1]);
        String[] arr = { "E", super.done, super.description, t };
        return arr;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.parseDateTime(this.at) + ")";
    }
}
