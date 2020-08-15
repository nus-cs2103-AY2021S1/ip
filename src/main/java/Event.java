public class Event extends Task {

    protected String event;

    protected Event(String task, String event) {
        super(task);
        this.event = event;
    }

    private String getEvent() {
        return this.event;
    }
}
