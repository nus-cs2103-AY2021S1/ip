package util.task;

/**
 * Event inherits from Task.
 * Event is a Task that has an added characteristic of a duration.
 */
public class Event extends Task {
    protected String duration;

    /**
     * Constructor.
     */
    public Event(String name, String details) {
        super(name);
        this.duration = details;
    }

    /**
     * Returns duration of the Event.
     *
     * @return Duration of the Event.
     */
    public String getEventDetails() {
        //return this.duration.format(formatter);
        return this.duration;
    }

    @Override
    public String toSaveData() {
        return "E - " + super.toSaveData() + " - " + this.duration + "\n";
    }

    @Override
    public String toString() {
        // By default print task name
        return "[E]" + super.toString() + " (at: " + getEventDetails() + ")";
    }
}
