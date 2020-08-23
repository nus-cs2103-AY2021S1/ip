import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDateTime at;

    public Events(String task, LocalDateTime at) {
        super(task);
        this.at = at;
    }

    public Events(String task, String at, boolean isDone) {
        super(task, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(at, formatter);
        this.at = dateTime;
    }

    @Override
    public String toString() {
        String dateTime = at.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
