package rock.task;

/**
 * A task that need to be done within a certain period.
 * Command syntax: dowithin + description + /between + startTime + /and + endTime
 * Example:        dowithin CS2103T Quiz /between every Friday /and the next Monday
 */
public class DoWithinPeriodTasks extends Task {
    private final String from;
    private final String to;

    /**
     * Constructor.
     * @param description The description of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     */
    public DoWithinPeriodTasks(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor with the addition of isDone.
     * @param isDone Check if the task is done.
     */
    public DoWithinPeriodTasks(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Represent the task in an audience-friendly form.
     * Format:  [W][isDone] + description + (from: from & to: to)
     * Example: [W][x] CS2103T IP (from: every Thursday & to: the next Monday)
     */
    @Override
    public String getStatus() {
        return "[W]" + getIcon() + " " + getDescription() + " (from: " + from + " & to: " + to + ")";
    }

    /**
     * Represent the task in a suitable form to store data.
     * Format:  W|description|isDone|from|to
     * Example: W|CS2103T IP|false|every Thursday|the next Monday
     */
    @Override
    public String getDataFormat() {
        return "W" + "|" + getDescription() + "|" + getIsDone() + "|" + from + "|" + to;
    }
}
