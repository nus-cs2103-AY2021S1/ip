package duke.task;

public class Event extends Task {
    private final String duration;

    /**
     * A Task with duration.
     * @param name name of Task
     * @param isDone whether Task is done
     * @param duration duration of Task as a String
     */
    public Event(String name, boolean isDone, String duration) {
        super(name, isDone);
        assert duration != null : "Duration is null";
        this.duration = duration;
    }

    /**
     * Returns String representation of Task presented to user.
     * @return String representation of Task presented to user
     */
    @Override
    public String toString() {
        String doneSymbol = getIsDone() ? "✓" : "✗";
        return String.format("[E][%s] %s (at: %s)", doneSymbol, getName(), duration);
    }

    /**
     * Compares two events and check if they are the same
     * @param o object to compare equality
     * @return true if the events are the same
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Event)) {
            return false;
        }
        Event e = (Event) o;
        return getName().equals(e.getName()) && duration.equals(e.duration);
    }

    /**
     * Returns String representation of Task to be saved.
     * @return String representation of Task to be saved
     */
    @Override
    public String toSaveString() {
        return String.format("E|%d|%s|%s", getIsDone() ? 1 : 0, getName(), duration);
    }
}
