package duke.tasks;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate eventTime;

    /**
     * Event constructor
     *
     * @param description Description string without date
     * @param eventTime Time of event LocalDate object
     * @param isDone Completion status of event
     */
    public Event(String description, LocalDate eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    public void edit(String editInput) throws DukeException {
        String editType = editInput.substring(0, 4);
        String editText = editInput.substring(5);
        if (editType.equalsIgnoreCase("desc")) {
            super.description = editText;
        } else if (editType.equalsIgnoreCase("time")) {
            eventTime = LocalDate.parse(editText);
        } else {
            throw new DukeException("Edit command error _(´ཀ`」 ∠)_");
        }
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + this.eventTime.format(FORMATTER) + ")";
    }
}
