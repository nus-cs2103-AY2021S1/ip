import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDateTime eventTime;

    public EventTask(String description, boolean isDone, String eventTime) {
        super(description, isDone);
        String[] splitEventTime= eventTime.split(" ");
        String inputEventTime = splitEventTime[0] + "T" + splitEventTime[1].substring(0,2) + ":" + splitEventTime[1].substring(2,4);
        this.eventTime = LocalDateTime.parse(inputEventTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma")) + ")";
    }
}
