/**
 * <h1> Event Task class </h1>
 *
 * The event class is a child class of Task
 * It contains an extra property: event that indicates
 * the date that a task is happening.
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class Event extends Task {

    protected String event;

    protected Event(String task, String event) {
        super(task);
        this.event = event;
    }

    private String getEvent() {
        return this.event;
    }

    @Override
    public String toString() {
        String done = this.done ? "\u2713" : "\u2718";
        return "[E][" + done + "] " + this.task + "(at:" + this.event + ")";
    }
}
