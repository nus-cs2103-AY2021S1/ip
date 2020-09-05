import java.time.LocalDate;

public class DeadlineTask extends Task {
    private LocalDate deadline;

    public DeadlineTask(String summary, LocalDate deadline) {
        super(summary);
        this.deadline = deadline;
        this.taskType = TaskType.DEADLINE;
    }

    public String getDeadlineString() {
        return deadline.getDayOfMonth() + " "
                + deadline.getMonth().toString() + " "
                + deadline.getYear();
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineString() + ")";
    }
}