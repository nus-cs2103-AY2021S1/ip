public class Deadline extends Task {
    String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        Task.totalTasks++;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.trim() + ")";
    }
}