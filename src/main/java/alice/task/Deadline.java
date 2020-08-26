package alice.task;

import alice.AliceException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter D_DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEEE, MMM dd uuuu, ha");
    private static final DateTimeFormatter D_DATE_FORMAT = DateTimeFormatter.ofPattern("EEEE, MMM dd uuuu");

    private final LocalDateTime by;
    private final boolean includesTime;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.includesTime = !by.toLocalTime().equals(LocalTime.MIDNIGHT);
    }

    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
        this.includesTime = !by.toLocalTime().equals(LocalTime.MIDNIGHT);
    }

    public String getDeadlineDateTime() {
        if (includesTime) {
            return by.format(D_DATETIME_FORMAT);
        } else {
            return by.format(D_DATE_FORMAT);
        }
    }

    public static Deadline decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 3) {
            return new Deadline(isDone, inputs[1], LocalDateTime.parse(inputs[2]));
        } else {
            throw new AliceException("Corrupted deadline data");
        }
    }

    @Override
    public String encode() {
        return "D | " + super.encode() + " | " + by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineDateTime() + ")";
    }
}
