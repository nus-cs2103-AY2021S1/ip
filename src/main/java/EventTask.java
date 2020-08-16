public class EventTask extends DukeTask {
    private final String datetime;

    public EventTask(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)",datetime);
    }
}
