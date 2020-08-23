import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate date;

    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = formatter.format(this.date);
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }
}
