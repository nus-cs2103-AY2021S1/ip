package duke;

/**
 * Provide support for managing tasks that need to be done within a certain period
 * e.g., collect certificate between Jan 15 and 25th.
 */
public class DoWithinPeriodTasks extends Task {
    String description;
    String from;
    String to;
    boolean isDone;

    /**
     * Constructor
     */
    public DoWithinPeriodTasks(String description, String from, String to) {
        super(description);
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     *
     */
    /**
     * Status in format [W][x] return book (from 6 June to 9 June)
     */
    @Override
    public String getStatus() {
        return "[W]" + super.getStatus() + " (from " + from + " to " + to + ")";
    }

    /**
     * type of Task -> D for deadline
     */
    @Override
    public String getType() {
        return "W";
    }

    /**
     * description to write to data file
     */
    @Override
    public String getDescription() {
        return super.getDescription() + "|" + this.from + "|" + this.to;
    }
}