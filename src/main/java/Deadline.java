import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {

    public Deadline(String description, LocalDate date) {
        super(description);
        symbol = "[D]";
        this.date = date;
    }

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        symbol = "[D]";
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return (getSymbol() + super.toString() + " (by: " + getDate() + ")");
    }
}
