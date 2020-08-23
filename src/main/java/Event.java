import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task implements Saveable {
    LocalDate date;

    Event(String label, String date, boolean done) {
        super(label, done);
        // Remove the "at"
        this.date = LocalDate.parse(date);
    }

    @Override
    public String getInfo() {
        StringBuilder str = new StringBuilder();
        str.append("E");
        str.append(super.getInfo());
        str.append(super.separator);
        str.append(date.toString());
        return str.toString();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
