package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task. It has a description and a due timing attribute.
 */
public class Deadline extends Task {

    private static final String TYPE = "D";
    private static final String TYPE_ICON = "[D]";
    protected LocalDateTime by;

    /**
     * Creates a new Deadline Task with a specified description and due time. String time
     * Object has to have proper formatting "yyyy-MM-dd HH:mm" as it will be parsed
     * as a LocalDateTime Object.
     *
     * @param description Describes the Deadline Task.
     * @param by          The due timing of the Deadline Task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATE_TIME_INPUT_PATTERN));
        this.storeAs = storeNotDoneDeadline(description, by);
    }

    /**
     * Creates a Deadline Task with a specified done indicator, description and due time.
     * String time Object has to have proper formatting "yyyy-MM-dd HH:mm" as it will be parsed
     * as a LocalDateTime Object.
     *
     * @param done        Indicates whether the task has been done.
     * @param description Describes the Deadline Task.
     * @param by          The due timing of the Deadline Task.
     */
    public Deadline(String done, String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATE_TIME_INPUT_PATTERN));

        if (done.equals(DONE)) {
            this.isDone = true;
            this.storeAs = storeDoneDeadline(description, by);
        }
        this.storeAs = storeNotDoneDeadline(description, by);
    }

    /**
     * Returns a String representation of the Deadline Task.
     *
     * @return representation of the Deadline Task.
     */
    @Override
    public String toString() {
        return TYPE_ICON + super.toString() + showDeadlineTime();
    }

    private String storeDoneDeadline(String description, String by) {
        return TYPE + SEPARATOR + DONE + SEPARATOR + description + SEPARATOR + by;
    }

    private String storeNotDoneDeadline(String description, String by) {
        return TYPE + SEPARATOR + NOT_DONE + SEPARATOR + description + SEPARATOR + by;
    }
    private String showDeadlineTime() {
        return " (by: " + by.format(DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_PATTERN)) + ")";
    }
}
