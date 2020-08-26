/**
 * An Event is a kind of Task that occurs within a certain period/time range.
 *
 * @author jingyenloh
 */
public class Event extends Task {
    private String timeRange;
    private static final String SAVE_STRING = "EVENT|%s|%s|%s";

    public Event(String taskName, String timeRange) {
        super(taskName);
        this.timeRange = timeRange;
    }

    public Event(boolean isDone, String taskName, String timeRange) {
        super(isDone, taskName);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeRange);
    }

    /**
     * Serializes the Event into a String that is easy to parse. The String takes the form:
     * <code>Event|isDone|taskName|timeRange</code>.
     * @return A formatted String ready for saving into a file
     */
    @Override
    public String toSaveString() {
        return String.format(SAVE_STRING, super.isDone, super.taskName, this.timeRange);
    }
}
