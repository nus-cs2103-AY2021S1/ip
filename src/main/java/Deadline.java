import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate time;

    public Deadline(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
