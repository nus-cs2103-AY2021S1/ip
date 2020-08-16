package enums;

/**
 * Commands that can be used in <i>Duke</i>.
 */
public enum Command {
    /**
     * Terminates the running of <i>Duke</i>.
     */
    BYE,

    /**
     * Adds a {@code Deadline} task to the {@code TaskManager}.
     */
    DEADLINE,

    /**
     * Marks a {@code Task} as done.
     */
    DONE,

    /**
     * Adds an {@code Event} task to the {@code TaskManager}.
     */
    EVENT,

    /**
     * Lists all {@code Task}s in the {@code TaskManager}.
     */
    LIST,

    /**
     * Adds a {@code ToDo} task to the {@code TaskManager}.
     */
    TODO,
}
