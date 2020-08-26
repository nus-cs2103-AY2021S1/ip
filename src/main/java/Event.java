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

    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }

    public static Event decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 3) {
            return new Event(isDone, inputs[1], inputs[2]);
        } else {
            throw new AliceException("Corrupted Event data");
        }
    }

    @Override
    public String encode() {
        return "E | " + super.encode() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + getEventDateTime() + ")";
    }
}
