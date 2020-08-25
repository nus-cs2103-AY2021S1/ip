import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String date;
    private LocalDate localDate;

    public Deadline(String task, String date, boolean isCompleted) {
        super(task, isCompleted);
        this.date = date;
        localDate = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
