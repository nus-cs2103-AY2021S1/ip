import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DukeDateTime {

    private LocalDateTime dateTime;
    private boolean containsTime;

    public DukeDateTime(LocalDateTime dateTime, boolean containsTime) {
        this.dateTime = dateTime;
        this.containsTime = containsTime;
    }

    @Override
    public String toString() {
        return containsTime
                ? dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"))
                : dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
