import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    LocalDateTime time;

    public Events(String description, String time) throws DateTimeParseException {
        super(description);
        this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    public Events(String description, String time, Boolean isDone) {
        super(description, isDone);
        this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }


    @Override
    public String toString() {
        return "[E]" + this.getIcon() + description + " (at: " +
                this.time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) + ")";
    }

    @Override
    public String toSaveString() {
        return String.format("E | %s | %s | %s",
                super.doneString(), this.description, this.time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }


}
