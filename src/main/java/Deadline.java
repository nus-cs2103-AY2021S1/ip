import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends TimedTask {

    public Deadline(String description, String by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.formatBy() + ")";
    }
}
