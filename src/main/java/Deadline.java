import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate time;

    Deadline(String description, String time) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate myDateObj = LocalDate.parse(time, formatter);
        this.time = myDateObj;
    }

    @Override
    public String toString() {
        String timeStr = this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D]%s(by: %s)", super.toString(), timeStr);
    }

    @Override
    public String toSaveString() {
        return String.format("%s || deadline || %s || %s", super.toSaveString(), this.description, this.time);
    }
}
