public class Event extends Task {
    private String date;

    private Event (String task, String date, boolean isDone) {
        super(task, isDone);
        this.date = date;
    }

    public Event(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public Event markDone() {
        return new Event(task, date, true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", date);
    }
}