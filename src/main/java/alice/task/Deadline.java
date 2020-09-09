package alice.task;

import alice.AliceException;
import alice.task.time.TaskDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final TaskDateTime by;

    /**
     * Creates an undone task with the specified deadline.
     *
     * @param description describes the task to be done before the deadline.
     * @param by          the latest datetime by which the task should be completed.
     */
    public Deadline(String description, TaskDateTime by) {
        this(false, description, by);
    }

    /**
     * Creates a task with the specified deadline and completion status.
     *
     * @param isDone      the completion status of the task, true if completed; false otherwise.
     * @param description describes the task to be done before the deadline.
     * @param by          the latest datetime by which the task should be completed.
     */
    public Deadline(boolean isDone, String description, TaskDateTime by) {
        super(isDone, description);
        this.by = by;

        assert !description.isBlank() : "Cannot create a Deadline with no description";
    }

    /**
     * Decode an encoded string representation of the {@code Deadline}.
     *
     * @param saved the string representation of the encoded task with deadline.
     * @return the {@code Deadline} described in the string representation.
     * @throws AliceException if the encoded string is corrupted.
     */
    public static Deadline decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 3) {
            return new Deadline(isDone, inputs[1], TaskDateTime.decode(inputs[2]));
        } else {
            throw new AliceException("Corrupted deadline data");
        }
    }

    @Override
    public String encode() {
        return "D | " + super.encode() + " | " + by.encode();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
