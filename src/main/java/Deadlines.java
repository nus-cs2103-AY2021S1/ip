import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDate by;

    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String saveAs() { return "D | " + super.saveAs() + " | " + by;}

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM uuuu")) + ")";
    }
}