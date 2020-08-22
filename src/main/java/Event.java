public class Event extends Task {
    String eventTime;

    Event(String taskName, String eventTime) throws DukeException {
        super(taskName);
        this.eventTime = eventTime.trim();
    }

    @Override
    public String toString() {
        return "[EVENT] " + super.toString() + " | at: " + eventTime;
    }
}
