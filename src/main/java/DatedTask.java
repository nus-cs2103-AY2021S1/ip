import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatedTask extends Task {
    protected LocalDate date;

    public DatedTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public DatedTask(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toSave() {
        return super.toSave() + " | " + date;
    }

    @Override
    public String toString() {
        return super.toString() + " (date: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

