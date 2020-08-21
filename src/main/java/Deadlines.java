import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDateTime by;

    public Deadlines(String description, LocalDateTime cutTime) {
        super(description);
        this.by = cutTime;
    }

    public LocalDate getDate() {
        return by.toLocalDate();
    }


    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH : mm")) + ")";
    }
}
