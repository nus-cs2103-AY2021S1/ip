public class Event extends Task {
    Date date;
    Timing timing;

    public Event(String description, String timing) {
        super(description, TaskType.EVENT);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

    public Event(String description, String date, String timing) {
        super(description, TaskType.EVENT);
        this.date = new Date(date);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

    public Date getDate() {
        return date;
    }

    public Timing getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + date.toString() + ", " + timing.toString() + ")";
    }
}
