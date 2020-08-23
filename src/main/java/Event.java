import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate time;

    Event(String description, String time) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate myDateObj = LocalDate.parse(time, formatter);
        this.time = myDateObj;
    }

    @Override
    public String toString() {
        String timeStr = this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E]%s(at: %s)", super.toString(), timeStr);
    }

    @Override
    public String toSaveString() {
        return String.format("%s || event || %s || %s", super.toSaveString(), this.description, this.time);
    }
}
