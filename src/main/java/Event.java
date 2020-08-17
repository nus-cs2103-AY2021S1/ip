public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description, TaskType.EVENT);
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + date + ")";
    }
}
