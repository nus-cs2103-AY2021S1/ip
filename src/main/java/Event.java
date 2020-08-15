public class Event extends Task {
    String timing;

    public Event(String description, String timing) {
        super(description, TaskType.EVENT);
        this.timing = timing;
        Task.totalTasks++;
    }

    public String getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + timing + ")";
    }
}
