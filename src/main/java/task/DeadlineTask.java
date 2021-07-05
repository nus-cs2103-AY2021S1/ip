package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import util.SplitOperation;

/**
 * Inherits from task and represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    /**
     * Deadline of the task.
     */
    private final LocalDateTime deadline;

    /**
     * Creates a deadline task.
     *
     * @param description Description of the task.
     * @param isDone State of whether the task is done.
     * @param deadline Deadline of the task.
     */
    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        // formats deadline date and time to the correct format, for example: 2007-12-03T10:15:30
        SplitOperation deadlineSplit = (input) -> {
            String[] splitInput = input.split(" ");
            return splitInput[0] + "T" + splitInput[1].substring(0, 2) + ":"
                    + splitInput[1].substring(2, 4);
        };
        this.deadline = LocalDateTime.parse(deadlineSplit.operate(deadline));
    }

    /**
     * Return a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n(by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma"))
                + ")";
    }
}
