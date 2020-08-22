public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time =time;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getDate() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), time);
    }
}
