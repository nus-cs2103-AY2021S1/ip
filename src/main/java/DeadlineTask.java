import java.time.LocalDate;

public class DeadlineTask extends Task {
    private LocalDate deadline;

    public DeadlineTask(String summary, LocalDate deadline) {
        super(summary);
            this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline.getDayOfMonth() + " "
                + deadline.getMonth().toString() + " "
                + deadline.getYear();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }
}