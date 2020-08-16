import java.text.MessageFormat;

/**
 * A specialised {@code Task} that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    private final String dueDate;

    /**
     * Constructs a new uncompleted {@code Deadline} object.
     *
     * @param name the name of the {@code Deadline}.
     * @param dueDate a string representing the due date of the {@code Deadline}.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a new {@code Deadline} object.
     *
     * @param name the name of the {@code Deadline}.
     * @param dueDate a string representing the due date of the {@code Deadline}.
     * @param isDone whether the {@code Deadline} has been completed.
     */
    private Deadline(String name, String dueDate, boolean isDone) {
        super(name, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Marks the {@code Deadline} as done.
     *
     * @return a new completed {@code Deadline} object with the same {@code name} as this {@code Deadline}.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(name, dueDate, true);
    }

    /**
     * Returns a string representation of this {@code Deadline} object.
     *
     * @return a string representation of the {@code Deadline}.
     */
    @Override
    public String toString() {
        return MessageFormat.format(ResourceHandler.getString("deadline.toString"), super.toString(), dueDate);
    }
}
