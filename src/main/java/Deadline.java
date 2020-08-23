import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    public Deadline(String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    public Optional<LocalDate> getDate() {
        return Optional.of(deadlineDate);
    }

    @Override
    public String toString() {
        return "D|" + super.toString() + "|" +
                deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + "|" +
                deadlineTime.format(DateTimeFormatter.ofPattern("hh.mma"));
    }
}
