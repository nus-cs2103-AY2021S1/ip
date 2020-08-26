import java.time.LocalDate;

public class Event extends Task {

    private LocalDate date;

    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date, Task.INPUT_DATE_TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(PRINT_DATE_TIME_FORMAT) + ")";
    }
}