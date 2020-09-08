import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task{

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public String dateTimeFormat(){
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.dateTimeFormat() + ")";
    }
}
