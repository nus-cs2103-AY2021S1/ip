public class Event extends Task {
    protected String time;

    public Event(String taskname, boolean status, String time) {
        super(taskname, status);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
