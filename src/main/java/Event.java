import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime durationFormatted;

    public Event (String task, String duration) {
        super(task, Tasktype.EVENT, duration);
        this.durationFormatted = LocalDateTime.parse(duration, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public Event (String task, String duration, boolean isDone) {
        super(task, Tasktype.EVENT, duration, isDone);
        this.durationFormatted = LocalDateTime.parse(duration, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                durationFormatted.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")));
    }

}

