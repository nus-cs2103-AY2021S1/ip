public class Event extends Task {
    public Date eventDate;
    public Timing eventTime;

    public Event(String description, String eventTime) {
        super(description, TaskType.EVENT);
        this.eventTime = new Timing(eventTime);
        Task.totalTasks++;
    }

    public Event(String description, String eventDate, String eventTime) {
        super(description, TaskType.EVENT);
        this.eventDate = new Date(eventDate);
        this.eventTime = new Timing(eventTime);
        Task.totalTasks++;
    }

    public Event(int boolDone, String description, String eventDate, String eventTime) {
        super(description, TaskType.EVENT, boolDone);
        this.eventDate = new Date(eventDate);
        this.eventTime = new Timing(eventTime);
        Task.totalTasks++;
    }

    public Date getDate() {
        return eventDate;
    }

    public Timing getTiming() {
        return eventTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + eventDate.toString() + ", " + eventTime.toString() + ")";
    }
}
