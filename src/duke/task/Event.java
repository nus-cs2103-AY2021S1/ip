package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    /** the time by which the task starts */
    protected LocalDate at;

    /**
     * Creates a Event with the specified description and event time.
     *
     * @param description the description of the task
     * @param at the time by which the task should starts at.
     * @throws DukeException if the event time cannot be parsed.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Datetime could not be recognised. Use yyyy-mm-dd format e.g. 2019-10-15");
        }
    }

    /**
     * Creates a Event with the specified description and event time.
     *
     * @param description the description of the task
     * @param isDone the completion status of the task.
     * @param at the time by which the task should starts at.
     * @throws DukeException if the event time cannot be parsed.
     */
    public Event(String description, boolean isDone, String at) throws DukeException  {
        super(description, isDone);
        try {
            this.at = LocalDate.parse(at);
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
        return super.getPlainText() + " | " + at;
    }

    @Override
    public String toString() {
        String formattedAt = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return "[E]" + super.toString() + " (at: " + formattedAt + ")";
    }
}