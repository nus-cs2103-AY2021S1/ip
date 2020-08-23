import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate date;

    public Event(String description, LocalDate date) {
        super(description, TaskType.EVENT);
        this.date = date;
    }

    public Event(String description, LocalDate date, boolean isDone) {
        super(description, isDone, TaskType.TODO);
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
    public String getData() {
        return String.format("%s_%s ", super.getData(), date);
    } 

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getFormattedDate() + ")";
    }
}
