package duke.tasks;

import java.text.MessageFormat;
import java.util.Arrays;

import duke.utils.Store;

/**
 * Represents a task within <i>Duke</i>.
 */
public abstract class Task {
    /** Name of the {@code Task}. */
    protected final String name;
    /** Whether the {@code Task} has been completed. */
    private final boolean isDone;

    /**
     * Constructs a new uncompleted {@code Task} object.
     *
     * @param name the name of the {@code Task}.
     */
    protected Task(String name) {
        this(name, false);
    }

    /**
     * Constructs a new {@code Task} object.
     *
     * @param name the name of the {@code Task}.
     * @param isDone whether the {@code Task} has been completed.
     */
    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the {@code Task} as done.
     *
     * @return a new completed {@code Task} object with the same {@code name} as this {@code Task}.
     */
    public abstract Task markAsDone();

    /**
     * Returns whether the {@code Task} is done.
     *
     * @return whether the {@code Task} is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks whether the {@code Task} contains any of the keywords in its name.
     *
     * @param keywords the keywords that are being searched.
     * @return whether the {@code Task} contains any of the keywords in its name.
     */
    public boolean matchesKeywords(String... keywords) {
        return Arrays.stream(keywords).anyMatch(keyword -> name.toLowerCase().contains(keyword.toLowerCase()));
    }

    /**
     * Returns a string representation of this {@code Task} object.
     *
     * @return a string representation of the {@code Task}.
     */
    @Override
    public String toString() {
        String key = isDone ? "task.toString.done" : "task.toString.notDone";
        return MessageFormat.format(Store.getResourceHandler().getString(key), name);
    }
}
