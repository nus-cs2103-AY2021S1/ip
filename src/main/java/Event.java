public class Event extends Task {
    private String date;

    Event(String taskName, boolean isCompleted, String date) {
        super(taskName, isCompleted);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
