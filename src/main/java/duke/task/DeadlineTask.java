package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    /**
     * Deadline of task.
     */
    private LocalDateTime deadline;

    /**
     * Constructs a new instance of an DeadlineTask.
     * @param description Description of deadline task.
     * @param deadline Deadline of deadline task.
     * @throws InvalidDateException If deadline is not in the form of "dd-MM-yyyy HH:mm".
     */
    public DeadlineTask(String description, String deadline) throws InvalidDateException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException exception) {
            throw new InvalidDateException();
        }

    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String parseTaskToText() {
        return "D" + " | " + (getCompletionStatus() ? "1" : "0") + " | " + getDescription()
                + " | " + deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                + " | " + getPriority();
    }

    @Override
    public String toString() {
        return getPriority().isEmpty()
            ? "[D][" + getStatusIcon() + "] " + description + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")"
            : "[D][" + getStatusIcon() + "] " + description + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")"
                + " Priority: " + getPriority();
    }
}
