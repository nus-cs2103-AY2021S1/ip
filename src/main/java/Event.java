import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String at;
    int code = 2;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = LocalDate.parse(this.at);
    }


    public static void invalidInput() {
        invalidInput("OOPS!!! The format of the Event is wrong.");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.dateFormatted() + ")";
    }
}
