import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");
    private final LocalDateTime time;

    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public Deadline(String name, String time, boolean doneState) {
        super(name, doneState);
        this.time = LocalDateTime.parse(time);
    }

    @Override
    public String write() {
        return String.format("D,%s%s", time, super.write());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                time.format(FORMATTER));
    }
}
