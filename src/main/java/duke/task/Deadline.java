package duke.task;

import duke.exception.WrongFormatException;

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
     * @param by The date and time that the task should be completed by.
     * @throws WrongFormatException If no description is provided.
     */
    public Deadline(String description, String by) throws WrongFormatException {
        super(description, "[D]", "deadline", false);
        String[] dateAndTimeParts = by.split(" ");
        String byDate = dateAndTimeParts[0];
        String byTime = dateAndTimeParts[1];
        this.date = LocalDate.parse(byDate);
        this.dateAndTime = this.date.atTime(Integer.parseInt(byTime.substring(0,2)),
                Integer.parseInt(byTime.substring(2,4)));
    }

    /**
     * Creates and initializes a deadline task that can be marked as completed.
     *
     * @param description The description of the task.
     * @param by The date and time that the task should be completed by.
     * @param isDone Marks whether the task has been completed or not.
     * @throws WrongFormatException If no description is provided.
     */
    public Deadline(String description, String by, boolean isDone) throws WrongFormatException {
        super(description, "[D]", "deadline", isDone);
        String[] dateAndTimeParts = by.split(" ");
        String byDate = dateAndTimeParts[0];
        String byTime = dateAndTimeParts[1];
        this.date = LocalDate.parse(byDate);
        this.dateAndTime = this.date.atTime(Integer.parseInt(byTime.substring(0,2)),
                Integer.parseInt(byTime.substring(2,4)));
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
    public String toString() {
        return super.toString() + " (by: " + dateAndTime.format(DateTimeFormatter.ofPattern("d MMM yyyy @ hh:mma"))
                + ")";
    }
}