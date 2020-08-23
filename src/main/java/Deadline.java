public class Deadline extends Task {
    protected Date deadlineDate;
    protected Timing deadlineTime;

    public Deadline(String description, String deadlineDate) {
        super(description, TaskType.DEADLINE);
        this.deadlineDate = new Date(deadlineDate);
        Task.totalTasks++;
    }

    public Deadline(String description, String deadlineDate, String deadlineTime) {
        super(description, TaskType.DEADLINE);
        this.deadlineDate = new Date(deadlineDate);
        this.deadlineTime = new Timing(deadlineTime);
        Task.totalTasks++;
    }
    public Deadline(int boolDone, String description, String deadlineDate, String deadlineTime) {
        super(description, TaskType.DEADLINE, boolDone);
        this.deadlineDate = new Date(deadlineDate);
        this.deadlineTime = new Timing(deadlineTime);
        Task.totalTasks++;
    }

    public Date getDate() {
        return deadlineDate;
    }

    public Timing getTiming() {
        return deadlineTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadlineDate.toString() + ", " + deadlineTime.toString() + ")";
    }
}
