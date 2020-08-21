public class Deadline extends Task {
    String date;

    public Deadline(String description, String date) {
        super(description, TaskType.DEADLINE);
        this.date = date;
        Task.totalTasks++;
    }

    public Deadline(String description, String date, int doneFlag) {
        super(description, TaskType.DEADLINE, doneFlag);
        this.date = date;
        Task.totalTasks++;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + date.trim() + ")";
    }
}
