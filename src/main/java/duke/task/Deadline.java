package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the deadline task type. A deadline task has a description of the task and the date and time of when
 * the task should be completed by.
 */
public class Deadline extends Task {

    /** The task type symbol that is used in the program to represent a deadline task */
    public static final String TASK_TYPE_SYMBOL = "[D]";

    private static final String TASK_TYPE_NAME = "deadline";
    private static final String DATE_TIME_FORMAT = "d MMM yyyy @ hh:mma";

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
        this(description, deadlineDate, deadlineDateAndTime, false);
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
        super(description,
                " (by: " + deadlineDateAndTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + ")",
                "|" + deadlineDateAndTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                TASK_TYPE_SYMBOL,
                TASK_TYPE_NAME,
                isDone);
        this.date = deadlineDate;
        this.dateAndTime = deadlineDateAndTime;
    }
}
