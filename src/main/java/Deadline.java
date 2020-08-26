import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter D_DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEEE, MMM dd uuuu, ha");

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    public String getDeadlineDateTime() {
        return by.format(D_DATETIME_FORMAT);
    }

    public static Deadline decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 3) {
            return new Deadline(isDone, inputs[1], inputs[2]);
        } else {
            throw new AliceException("Corrupted Deadline data");
        }
    }

    @Override
    public String encode() {
        return "D | " + super.encode() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineDateTime() + ")";
    }
}
