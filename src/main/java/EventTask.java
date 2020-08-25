import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends DukeTask {
    private final LocalDateTime datetime;

    public EventTask(String description, LocalDateTime datetime) {
        super(description);
        this.datetime = datetime;
    }

    public String getDatetime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(CommonString.DUKE_DATETIME_FORMAT.toString());
        return datetime.format(df);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", getDatetime());
    }
}
