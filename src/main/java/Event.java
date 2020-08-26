import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.type = "E";
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.at = LocalDate.parse(at,inputFormat);
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone,"E");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.at = LocalDate.parse(at,inputFormat);
    }

    @Override
    public String toString() {

        String atTime = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return super.toString() + "(at: " + atTime + ")";

    }
}