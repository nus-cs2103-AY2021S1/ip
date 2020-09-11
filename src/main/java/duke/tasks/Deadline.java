package duke.tasks;

import java.util.Objects;
import java.util.Optional;

/**
 * The class for deadline object.
 */
public class Deadline extends Task {

    private String deadline;

    /**
     * Default constructor for deadline.
     * @param task
     * @param deadline
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Constructor with status.
     * @param status
     * @param task
     * @param deadline
     */
    public Deadline(String status, String task, String deadline) {
        super(task, Objects.equals(status, "1") ? true : false);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + deadline + ")";
    }

    @Override
    public Optional<String> getTime() {
        return Optional.of(deadline);
    }
}
