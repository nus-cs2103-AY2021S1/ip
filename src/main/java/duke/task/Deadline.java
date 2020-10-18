package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private String byDateString;
    private LocalDate byDate;

    /**
     * Creates a {@code Deadline} with given task content and date.
     *
     * @param taskContent A String of content.
     * @param byDateString A String of date.
     */
    public Deadline(String taskContent, String byDateString) {
        super(taskContent);
        this.byDateString = byDateString;
    }

    /**
     * Creates a {@code Deadline} with given task content and date.
     *
     * @param taskContent A String of content.
     * @param byDate A LocalDate of date.
     */
    public Deadline(String taskContent, LocalDate byDate) {
        super(taskContent);
        this.byDate = byDate;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDate() {
        if (byDateString != null) {
            return byDateString;
        } else {
            return byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate() + ")";
    }
}
