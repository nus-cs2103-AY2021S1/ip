public class Event extends Task {

    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public Event(String description, String date, boolean isCompleted) {
        super(description, isCompleted);
        this.date = date;
    }

    @Override
    public Event markCompleted() {
        return new Event(description, date, true);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }
}
