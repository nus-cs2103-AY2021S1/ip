package duke.task;

/**
 * Encapsulates the event task type. An event task has a description of the task and the venue of where the task will
 * be done.
 */
public class Event extends Task {

    /** The task type symbol that is used in the program to represent an event task */
    public static final String TASK_TYPE_SYMBOL = "[E]";

    private static final String TASK_TYPE_NAME = "event";

    /** The venue where the task is to be done */
    protected String at;

    /**
     * Creates and initializes an event task that has not been completed by default.
     *
     * @param description The description of the task.
     * @param at The venue where the task is to be done.
     */
    public Event(String description, String at) {
        this(description, at, false);
    }

    /**
     * Creates and initializes an event task that can be marked as completed.
     *
     * @param description The description of the task.
     * @param at The venue where the task is to be done.
     * @param isDone Marks whether the task has been completed or not.
     */
    public Event(String description, String at, boolean isDone) {
        super(description,
                " (at: " + at + ")",
                "|" + at,
                TASK_TYPE_SYMBOL,
                TASK_TYPE_NAME,
                isDone);
        this.at = at;
    }

    /**
     * Returns the venue where the task is to be done.
     *
     * @return The venue where the task is to be done.
     */
    public String getAt() {
        return at;
    }
}
