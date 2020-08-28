package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Constructs a <code>Deadline</code> object.
     * @param description The description of the task with deadline
     * @param deadline The deadline of the task
     */
    public Deadline(String description, LocalDateTime deadline) {

        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a <code>Deadline</code> object knowing it is already done.
     * @param description The description of the task with deadline
     * @param deadline The deadline of the task
     * @param isDone Whether the deadline task is done or not
     */
    public Deadline(String description, LocalDateTime deadline, boolean isDone) {

        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy, HH:mm");
        return super.toString() + " (by: " + deadline.format(formatter) + ")";
    }

    @Override
    public String simplifiedTaskString() {
        return super.simplifiedTaskString() + " - "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}
