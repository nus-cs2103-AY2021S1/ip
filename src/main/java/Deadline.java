import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.deadlineDate = date;
        this.deadlineTime = time;
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
