public class Event extends Task {
    protected String eventDate;

    public Event(String taskName, String eventDate) {
        super(taskName, "E");
        this.eventDate = eventDate;
    }
    
    public String getEventDate() {
        return this.eventDate;
    }

    @Override
    public String toString() {
        String check;
        if (done == true) {
            check = "✓";
        } else {
            check = "✗";
        }
        return "[" + taskType + "][" + check + "] " + taskName + "(at:" + eventDate + ")";
    }
}
