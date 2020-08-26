package duke.task;

public class Event extends Task {
    private final String duration;

    /**
     * A Task with duration.
     * @param name name of Task
     * @param done whether Task is done
     * @param duration duration of Task as a String
     */
    public Event(String name, boolean done, String duration) {
        super(name, done);
        this.duration = duration;
    }

    /**
     * Returns String representation of Task presented to user.
     * @return String representation of Task presented to user
     */
    @Override
    public String toString() {
        String doneSymbol = isDone() ? "✓" : "✗";
        return String.format("[E][%s] %s (at: %s)", doneSymbol, getName(), duration);
    }

    /**
     * Returns String representation of Task to be saved.
     * @return String representation of Task to be saved
     */
    @Override
    public String toSaveString() {
        return String.format("E|%d|%s|%s", isDone() ? 1 : 0, getName(), duration);
    }
}
