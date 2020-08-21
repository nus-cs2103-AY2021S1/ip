public class Deadline extends Task {
    Date date;
    Timing timing;

    public Deadline(String description, String date) {
        super(description, TaskType.DEADLINE);
        this.date = new Date(date);
        Task.totalTasks++;
    }

    public Deadline(String description, String date, String timing) {
        super(description, TaskType.DEADLINE);
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
        return super.toString() + " (by: " + date.toString() + ", " + timing.toString() + ")";
    }
}
