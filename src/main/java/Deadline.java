import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends TaskDDL {

    public Deadline(String task, LocalDate ddl) {
        super(task, ddl);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getDateTime());
    }
}
