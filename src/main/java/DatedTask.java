import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatedTask extends Task {
    protected LocalDate date;

    public DatedTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public DatedTask(String name, boolean completed, LocalDate date) {
        super(name, completed);
        this.date = date;
    }

    @Override
    public String format() {
        return super.format() + SAVE_DELIMITER + this.date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }
}
