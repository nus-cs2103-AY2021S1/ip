import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime time;

    Deadline(String description, String time) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime myDateObj = LocalDateTime.parse(time, formatter);
        this.time = myDateObj;
    }

    @Override
    public String toString() {
        String timeStr = this.time.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy"));
        return String.format("[D]%s(by: %s)", super.toString(), timeStr);
    }

    @Override
    public String toSaveString() {
        return String.format("%s || deadline || %s || %s", super.toSaveString(), this.description, this.time);
    }
}
