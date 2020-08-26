import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime on;
    private static final DateTimeFormatter E_DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEEE, MMM dd uuuu, ha");

    public Event(String description, LocalDateTime on) {
        super(description);
        this.on = on;
    }

    public String getEventDateTime() {
        return on.format(E_DATETIME_FORMAT);
    }

    public Event(boolean isDone, String description, LocalDateTime on) {
        super(isDone, description);
        this.on = on;
    }

    public static Event decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 3) {
            return new Event(isDone, inputs[1], LocalDateTime.parse(inputs[2]));
        } else {
            throw new AliceException("Corrupted Event data");
        }
    }

    @Override
    public String encode() {
        return "E | " + super.encode() + " | " + on.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + getEventDateTime() + ")";
    }
}
