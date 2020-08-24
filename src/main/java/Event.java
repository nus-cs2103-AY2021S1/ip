/**
 * Represents an event task at a certain time.
 */
public class Event extends Task{

    private String task;
    private String time;

    protected Event(String task, String time) {
        this.task = task;
        this.time = time;
    }

    /**
     * Returns the task description of this Event.
     *
     * @return String of task description.
     */
    @Override
    protected String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                super.isDone() ? "\u2713" : "\u2717",
                getTask(),
                time);
    }

}
