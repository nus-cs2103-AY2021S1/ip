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

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                time.format(formatter));
    }
}
