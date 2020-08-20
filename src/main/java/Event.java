public class Event extends Task {

    protected String event;

    protected Event(String task, String event) {
        super(task);
        this.event = event;
    }

    private String getEvent() {
        return this.event;
    }

    @Override
    public String toString() {
        String done = this.done ? "\u2713" : "\u2718";
        return "[E][" + done + "] " + this.task + "(at:" + this.event + ")";
    }
}
