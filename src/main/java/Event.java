public class Event extends Task {
    private String eventTime;

    public Event(String taskName, String eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    @Override
    public String getStorageFormat() {
        return "E | " + super.getStorageFormat() + " | " + eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }

    public static Event makeTaskFromInput(String taskName, String time) throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badEventTask();
        } else if (time.isBlank()) {
            throw DukeException.badEventDate();
        }

        return new Event(taskName, time);
    }
}
