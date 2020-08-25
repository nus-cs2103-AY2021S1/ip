public class Event extends Task {
    protected String duration;

    public Event(String name, String details) {
        super(name);
        this.duration = details;
    }

    // Gets duration of the task
    public String getEventDetails() {
        return this.duration;
    }

    @Override
    public String toSaveData() {
        return "E - " + super.toSaveData() + " - " + this.duration + "\n";
    }

    @Override
    public String toString() {
        // By default print task name
        return "[E]" + super.toString() + " (at: " + this.duration + ")";
    }
}
