package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task that needs to be done by a certain date and time.
 */
public class Deadline extends Task {

    /**
     * Constant that determines the representation of this task's type.
     */
    protected static final String TASK_TYPE = "D";

    /**
     * Constant that stores the date to finish the task by.
     */
    protected LocalDate dateBy;

    /**
     * constant that store the time to finish the task by.
     */
    protected LocalTime timeBy;

    /**
     * Initializes a new Deadline object.
     * Converts the date and time to attend the event to LocalDate and LocalTime objects.
     *
     * @param description       The task to be completed.
     * @param by                The date and time when it must be completed.
     * @throws DukeException    If the program fails at any point.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            String[] parts = by.split(" ", 2);
            this.dateBy = LocalDate.parse(parts[0]);
            this.timeBy = LocalTime.parse(parts[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input date and time in correct format: " +
                    "'yyyy-MM-dd HH:MM' (24-hour time format).");
        }
    }

    /**
     * Initializes a new Deadline object.
     * Converts the date and time to attend the event to LocalDate and LocalTime objects.
     *
     * @param description       The task to be completed.
     * @param by                The date and time when it must be completed.
     * @param isDone            Whether the task is done or not.
     * @throws DukeException    If the program fails at any point.
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);
        try {
            String[] parts = by.split(" ", 2);
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
     * Task is prepended by Deadline.TASK_TYPE.
     *
     * @return Data of the task in string format.
     */
    @Override
    public String toString() {
        String modifier = (this.timeBy.isAfter(LocalTime.NOON)) ? "pm" : "am";
        return String.format("[%s]%s (at: %s, %s)",
                Event.TASK_TYPE,
                super.toString(),
                this.dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.timeBy.format(DateTimeFormatter.ofPattern("hh:mm")) + modifier);
    }
}