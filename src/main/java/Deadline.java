import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    final LocalDate byDate;
    final LocalTime byTime;

    Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description, TaskType.DEADLINE);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    public LocalDate getDate() {
        return byDate;
    }

    @Override
    public String toString() {
        String time = byTime == null
                ? ""
                : ", " + byTime.format(DateTimeFormatter.ofPattern("h.mm a"));
        return super.toString() + " (by: "
                + byDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"))
                + time + ")";
    }
}