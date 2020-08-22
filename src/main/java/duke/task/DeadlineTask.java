package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a DeadlineTask that has to be done
 * by a certain datetime.
 */
public class DeadlineTask extends Task {
    private final LocalDate deadline; // YYYY-MM-DD

    /**
     * Initializes a new DeadlineTask.
     * @param description The description of the DeadlineTask.
     * @param deadline The deadline of the DeadlineTask. It should
     *                 be in the form YYYY-MM-DD.
     * @throws DukeException If the deadline format is wrong.
     */
    public DeadlineTask(String description, String deadline) throws DukeException {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date format should be YYYY-MM-DD");
        }
    }

    /**
     * Fully initializes a DeadlineTask.
     * @param description The description of the DeadlineTask.
     * @param isDone Indicates whether the DeadlineTask has been done.
     * @param deadline The deadline in LocalDate format of the DeadlineTask.
     */
    public DeadlineTask(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public DeadlineTask markAsDone() {
        return new DeadlineTask(description, true, deadline);
    }

    @Override
    public String getData() {
        return "D|" + super.getData() + "|" + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
