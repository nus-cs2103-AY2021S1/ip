public class Event extends Task {
    protected String eventTime;

    public Event(String event) {
        super(event.substring(6, event.indexOf("/")-1));
        this.eventTime = "at: " + event.substring(event.indexOf("/")+4);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]").append(super.toString()).append(" (").append(this.eventTime).append(")");
        return sb.toString();
    }
}
