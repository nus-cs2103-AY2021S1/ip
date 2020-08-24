import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends DukeTask {
    private final LocalDateTime datetime;

    public DeadlineTask(String description, LocalDateTime datetime) {
        super(description);
        this.datetime = datetime;
    }

    public String getDatetime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm:ss a");
        return datetime.format(df);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getDatetime());
    }
}