import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 *  Represents a deadline task.
 *  A deadline task has a deadline date and time.
 */
public class Deadline extends Task {
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    /**
     * Create a deadline task
     * @param description of task
     * @param deadlineDate of task
     * @param deadlineTime of task
     * @param notes of task
     */
    public Deadline(String description, LocalDate deadlineDate,
                    LocalTime deadlineTime, Optional<String> notes) {
        super(description, notes.map(note -> "|Note: " + note));
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns an Optional of the deadline date.
     * @return an Optional of deadline date.
     */
    public Optional<LocalDate> getDate() {
        return Optional.of(deadlineDate);
    }

    @Override
    public String toString() {
        return "D|" + super.toString() + "|"
                + deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + "|"
                + deadlineTime.format(DateTimeFormatter.ofPattern("hh.mma"))
                + notes.orElse("");
    }
}
