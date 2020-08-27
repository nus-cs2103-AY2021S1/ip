import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String dl; //deadline given
    protected LocalDate date;
    protected LocalTime time;

    public Event(String taskName, String dl) throws WrongDeadlineException {
        super(taskName);
        this.dl = dl;
        String[] temp = dl.split(" ", 2);
        try {
            this.date = LocalDate.parse(temp[0]);
            this.time = LocalTime.parse(temp[1]);
        } catch (DateTimeParseException e) {
            throw new WrongDeadlineException("event", "/at");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) +
                ", " +
                this.time.format(DateTimeFormatter.ofPattern("h.m a")) + ")";
    }
}
