import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;

    public Event(String description, LocalDate date) {
        super(description, TaskType.EVENT);
        this.date = date;
    }
    
    public boolean isDateEqual(LocalDate date) {
        return date.equals(this.date);
    }

    private String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getFormattedDate() + ")";
    }
}
