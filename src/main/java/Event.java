import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String at;
    protected LocalDate eventDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            this.eventDate = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            //do nothing
        }
    }
    public void printTime() {
        try {
            System.out.println(this.eventDate.getMonth().toString()
                    + " " + this.eventDate.getDayOfMonth()
                    + " " + this.eventDate.getYear());
        } catch (NullPointerException e) {
            System.out.println("No valid date available.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
