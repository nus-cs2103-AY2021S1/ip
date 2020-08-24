import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String duration;
    private LocalDateTime durationFormatted;


    public Event (String task, String duration) {
        super(task);
        this.duration = duration;
        this.durationFormatted = LocalDateTime.parse(duration, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                durationFormatted.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")));
    }

}

