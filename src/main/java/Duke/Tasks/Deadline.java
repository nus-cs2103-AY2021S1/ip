package duke.tasks;

import java.time.LocalDateTime;

import duke.main.Parser;

/**
 * The type Deadline.
 */
public class Deadline extends Task {

    /**
     * The By.
     */
    protected LocalDateTime by;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param done        the done
     * @param by          the by
     */
    public Deadline(String description, String done, LocalDateTime by) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String[] getStringArr() {
        String[] stringArr = this.by.toString().split("T");
        String[] timeArr = stringArr[1].split(":");
        String t = String.format("%s %s%s", stringArr[0], timeArr[0], timeArr[1]);
        String[] arr = { "D", super.done, super.description, t };
        return arr;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.parseDateTime(this.by) + ")";
    }
}
