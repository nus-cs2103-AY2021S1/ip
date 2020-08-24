/**
 * A Deadline class.
 * A Deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    private final TimeWrapper time;

    private Deadline(String description, TimeWrapper time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Returns a new uncompleted deadline.
     * @param description Description of deadline.
     * @param time Time by which the task is to be done.
     * @return New uncompleted deadline.
     */
    public static Deadline getDeadline(String description, String time) {
        return new Deadline(description, TimeWrapper.getTimeWrapper(time), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markComplete() {
        return new Deadline(description, time, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDataString() {
        return String.format("D|%d|%s|%s", isDone ? 1 : 0, description, time);
    }

    public boolean isSameTime(String date) {
        return time.equals(TimeWrapper.getTimeWrapper(date));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), time.toString());
    }
}
