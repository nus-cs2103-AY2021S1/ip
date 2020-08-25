public class Event extends Task {

    protected String date;

    public Event(String task, String date, boolean isCompleted) {
        super(task, isCompleted);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
