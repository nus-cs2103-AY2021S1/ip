package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the deadline task type. A deadline task has a description of the task and the date and time of when
 * the task should be completed by.
 */
public class Deadline extends Task {

    /** Date that the task should be completed by */
    protected LocalDate date;

    /** Date and time that the task should be completed by */
    protected LocalDateTime dateAndTime;

    /**
     * Creates and initializes a deadline task that has not been completed by default.
     *
     * @param description The description of the task.
     * @param deadlineDate The date of the deadline of the task.
     * @param deadlineDateAndTime The date and time of the deadline of the task.
     */
    public Deadline(String description, LocalDate deadlineDate, LocalDateTime deadlineDateAndTime) {
        super(description, "[D]", "deadline", false);
        this.date = deadlineDate;
        this.dateAndTime = deadlineDateAndTime;
    }

    /**
     * Creates and initializes a deadline task that can be marked as completed.
     *
     * @param description The description of the task.
     * @param deadlineDate The date of the deadline of the task.
     * @param deadlineDateAndTime The date and time of the deadline of the task.
     * @param isDone Marks whether the task has been completed or not.
     */
    public Deadline(String description, LocalDate deadlineDate, LocalDateTime deadlineDateAndTime, boolean isDone) {
        super(description, "[D]", "deadline", isDone);
        this.date = deadlineDate;
        this.dateAndTime = deadlineDateAndTime;
    }

    /**
     * Formats the string that will be written in the save file to represent this particular deadline task.
     *
     * @return The string that will be written in the save file to represent this particular deadline task.
     */
    @Override
    public String toStringForMemory() {
        return super.toStringForMemory() + "|" + dateAndTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Formats the way the deadline task is presented to the user as part of the task list.
     *
     * @return The String that represents the deadline task when it is presented to the user as part of the task list.
     */
    @Override
    public String toStringForGui() {
        return super.toStringForGui() + " (by: " + dateAndTime.format(DateTimeFormatter
                .ofPattern("d MMM yyyy @ hh:mma")) + ")";
    }

    @Override
    public String toStringForCli() {
        return super.toStringForCli() + " (by: " + dateAndTime.format(DateTimeFormatter
                .ofPattern("d MMM yyyy @ hh:mma")) + ")";
    }
}
