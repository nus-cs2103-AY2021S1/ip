import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Event extends Task {
    LocalDate time;

    DateTimeFormatter in = DateTimeFormatter.ISO_LOCAL_DATE;
    DateTimeFormatter out = DateTimeFormatter.ofPattern("MMM dd yyyy");

    Event(String name, String time) {
        super(name, Type.EVENT);
        try {
            this.time = LocalDate.parse(time, in);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(out) + ")";
    }
}
