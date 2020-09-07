package duke.tasks;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate deadline;

    /**
     * Deadline constructor
     *
     * @param description Description string without date
     * @param deadline Deadline LocalDate object
     * @param isDone Completion status of deadline
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public void edit(String editInput) throws DukeException {
        String editType = editInput.substring(0, 4);
        String editText = editInput.substring(5);
        if (editType.equalsIgnoreCase("desc")) {
            super.description = editText;
        } else if (editType.equalsIgnoreCase("time")) {
            deadline = LocalDate.parse(editText);
        } else {
            throw new DukeException("Edit command error _(´ཀ`」 ∠)_");
        }
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.deadline.format(FORMATTER) + ")";
    }
}
