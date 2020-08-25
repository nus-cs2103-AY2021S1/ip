package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        String eventAt = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(at);
        return "[E]" + super.toString() + " (at: " + eventAt + ")" + "\n";
    }
}
