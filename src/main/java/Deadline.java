import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// a task that need to specify a date to be done before the date
public class Deadline extends Task {

    protected LocalDate deadlineDate;

    /**
     * Deadline construction taking description and deadline date.
     * @param description
     * @param deadlineDate
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Deadline constructor taking boolean is done, description and deadline date.
     * @param isDone
     * @param description
     * @param deadlineDate
     */
    public Deadline(boolean isDone, String description, LocalDate deadlineDate) {
        super(isDone, description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public LocalDateTime getDueDateTime() {
        return LocalDateTime.of(deadlineDate, LocalTime.MAX);
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
