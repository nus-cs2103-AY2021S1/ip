package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task that has to be attended by a specified and time.
 */
public class Event extends Task {

    /**
     * Constant that determines the representation of this task's type.
     */
    protected static final String TASK_TYPE = "E";

    /**
     * Constant that stores the date to finish the task by.
     */
    protected LocalDate dateBy;

    /**
     * constant that store the time to finish the task by.
     */
    protected LocalTime timeBy;

    /**
     * Initializes a new Event object.
     * Converts the date and time to attend the event to LocalDate and LocalTime objects.
     *
     * @param description       The task to be completed.
     * @param at                The date and time when it must be attended.
     * @throws DukeException    If the program fails at any point.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            String[] parts = at.split(" ", 2);
            this.dateBy = LocalDate.parse(parts[0]);
            this.timeBy = LocalTime.parse(parts[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input date and time in correct format: " +
                    "'yyyy-MM-dd HH:MM' (24-hour time format).");
        }
    }

    /**
     * Initializes a new Event object.
     * Converts the date and time to attend the event to LocalDate and LocalTime objects.
     *
     * @param description       The task to be completed.
     * @param at                The date and time when it must be attended.
     * @param isDone            Whether the task is done or not.
     * @throws DukeException    If the program fails at any point.
     */
    public Event(String description, String at, boolean isDone) throws DukeException{
        super(description, isDone);
        try {
            String[] parts = at.split(" ", 2);
            this.dateBy = LocalDate.parse(parts[0]);
            this.timeBy = LocalTime.parse(parts[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input date and time in correct format: " +
                    "'yyyy-MM-dd HH:MM' (24-hour time format)");
        }
    }

    /**
     * Returns a string representation of the task in a save friendly format.
     * The way it is saved affects how the .txt file is read in {@link duke.Storage}
     *
     * @return Save-friendly string of the task.
     */
    @Override
    public String getSaveFormat() {
        return String.format("%s | %s | %s %s",
                Event.TASK_TYPE,
                super.getSaveFormat(),
                this.dateBy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                this.timeBy.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    /**
     * Returns a string representation of the task.
     * Task is prepended by Event.TASK_TYPE.
     *
     * @return Data of the task in string format.
     */
    @Override
    public String toString() {
        String modifier = (this.timeBy.isAfter(LocalTime.NOON)) ? "pm" : "am";
        return String.format("[%s]%s (by: %s, %s)",
                Deadline.TASK_TYPE,
                super.toString(),
                this.dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.timeBy.format(DateTimeFormatter.ofPattern("hh:mm")) + modifier);
    }
}