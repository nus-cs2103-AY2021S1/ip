import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDateTime timing;

    public Events(String taskDesc, LocalDateTime timing) {
        super(taskDesc);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                timing.format(DateTimeFormatter.ofPattern("HH:mm MMM d yyyy"))+ ")";
    }
}
