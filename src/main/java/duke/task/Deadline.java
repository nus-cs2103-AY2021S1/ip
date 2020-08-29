package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    /** the time by which the task needs to be completed by */
    protected LocalDate by;

    /**
     * Creates a Deadline with the specified description and deadline time.
     *
     * @param description the description of the task
     * @param by the time by which the task should be finished by.
     * @throws DukeException if the deadline time cannot be parsed.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Datetime could not be recognised. Use yyyy-mm-dd format e.g. 2019-10-15");
        }
    }
     /** Creates a Deadline with the specified description, completion status and deadline time.
      *
      * @param description the description of the task.
      * @param isDone the completion status of the task.
      * @param by the time by which the task should be finished by.
      * @throws DukeException if the deadline time cannot be parsed.
      */
    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Loading: Datetime could not be recognised. Use yyyy-mm-dd format e.g. 2019-10-15");
        }
    }

    /**
     * Returns a plainText formatted string representation of the task, for saving and loading into a text file.
     *
     * @return a plainText formatted string representation of the task.
     */
    @Override
    public String getPlainText() {
        return super.getPlainText() + " | " + by;
    }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}