package duke;

/**
 * A task that happens at a certain time.
 * Command syntax: event + description + /at + time
 * Example:        event CS2101 OP1 /at Week 5 Tutorial 2
 */
public class Event extends Task {
    private final String time;

    /**
     * Constructor.
     * @param description The description of the task.
     * @param time The time of the task.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructor with the addition of isDone.
     * @param isDone Check if the task is done.
     */
    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Represent the task in an audience-friendly form.
     * Format:  [E][isDone] + description + (at: time)
     * Example: [E][x] CS2101 OP1 (at: Week 5 Tutorial 2)
     */
    @Override
    public String getStatus() {
        return "[E]" + getIcon() + " " + getDescription() + " (at: " + time + ")";
    }

    /**
     * Represent the task in a suitable form to store data.
     * Format:  E|description|isDone|time
     * Example: E|CS2101 OP1|false|Week 5 Tutorial 2
     */
    @Override
    public String getDataFormat() {
        return "E" + "|" + getDescription() + "|" + getIsDone() + "|" + time;
    }
}
