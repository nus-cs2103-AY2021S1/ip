import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime time;
    private final DateTimeFormatter formatter;

    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
        formatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");
    }

    public Deadline(String name, String time, boolean doneState) {
        super(name, doneState);
        this.time = time;
    }

    @Override
    public String write() {
        return String.format("D,%s%s", time, super.write());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                time.format(formatter));
    }
}
