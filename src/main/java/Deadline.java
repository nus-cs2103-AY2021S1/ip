import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateEnd;

    public Deadline(String description, LocalDateTime dateEnd) {
        super(description);
        this.dateEnd = dateEnd;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), dateEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
