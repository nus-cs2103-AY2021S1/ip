import java.util.Objects;

/**
 * Abstract class to represent any type of task. All task objects will have a name, and a boolean attribute done.
 */
public abstract class Task {
    private final String name;
    private boolean Done = false;
    private static final String MESSAGE_BLANK_TASK = "Did you casually forget to put in the description of the task?";

    /**
     * Public Constructor for a task object.
     *
     * @param name Name of the task.
     * @throws BlankTaskException If task provided has a blank name.
     */
    public Task(String name) throws BlankTaskException {
        if (name.isBlank()) {
            throw new BlankTaskException(MESSAGE_BLANK_TASK);
        }
        this.name = name.strip();
    }

    /**
     * Gets the name of the task.
     *
     * @return Name of task.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return (isDone() ? "[✓] " : "[✗] ") + name;
    }

    /**
     * Returns true if the task is completed.
     *
     * @return true if task is done, false otherwise.
     */
    public boolean isDone() {
        return Done;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        Done = true;
    }

    /**
     * Returns an array representing the list of attributes for the task.
     *
     * @return A list of attributes.
     */
    public abstract String[] attributeList();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Done == task.Done &&
                Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Done);
    }
}
