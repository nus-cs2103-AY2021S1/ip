package nekochan.model.task;

import nekochan.Encodable;
import nekochan.Searchable;
import nekochan.exceptions.NekoTaskCreationException;
import nekochan.util.Messages;

/**
 * The {@code Task} class provides a skeletal implementation of an immutable object representing a task.
 * Implements the {@code Encodable} and {@code Searchable} interface.
 */
public abstract class Task implements Encodable<Task>, Searchable {

    protected static final String ENCODED_COMPLETE_FLAG = "Y";
    protected static final String ENCODED_INCOMPLETE_FLAG = "N";
    private static final String COMPLETED_ICON = "\u2713";
    private static final String INCOMPLETE_ICON = "\u2718";


    protected final String description;
    protected final boolean isCompleted;

    /**
     * Constructs an immutable instance of a not completed task.
     *
     * @param description the description of the task.
     * @throws NekoTaskCreationException if the {@code description} is empty.
     */
    protected Task(String description) throws NekoTaskCreationException {
        this(description, false);
    }

    /**
     * Constructs an immutable instance of a task.
     *
     * @param description the description of the task.
     * @param isCompleted the completion status of the task.
     * @throws NekoTaskCreationException if the {@code description} is empty.
     */
    protected Task(String description, boolean isCompleted) throws NekoTaskCreationException {
        if (description.trim().length() == 0) {
            throw new NekoTaskCreationException(Messages.PARSE_TASK_DESCRIPTION_ERROR);
        }
        this.description = description;
        this.isCompleted = isCompleted;
    }

    private String getStatusIcon() {
        return isCompleted ? COMPLETED_ICON : INCOMPLETE_ICON;
    }

    /**
     * Marks this {@code Task} as complete.
     *
     * @return a new immutable instance of this {@code Task} that is marked as complete.
     */
    public abstract Task setCompleted();

    abstract boolean isSimilar(Object obj);

    abstract boolean isDuplicate(Object obj);

    /**
     * Returns a new instance of this {@code Task} with the same details.
     *
     * @return a new instance of this {@code Task} with the same details.
     */
    public abstract Task deepCopy();

    /**
     * Returns a string representation of this {@code Task}.
     * Contains the description and completion status of this {@code Task}.
     *
     * @return a string representation of this {@code Task}.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
