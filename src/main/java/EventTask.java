import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    LocalDate at;
    static final String TASK_TYPE = "E";

    public EventTask(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", TASK_TYPE, getStatusIcon(), description,
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
