import java.time.LocalDate;

public class Event extends TimedTask {
    private static final String TAG = "[E]";

    public Event (String description, String at) {
        super(description, at, TAG);
    }

    public Event (String description, String time, LocalDate date) {
        super (description, time, date, TAG);
    }
}
