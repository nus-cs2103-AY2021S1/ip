import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime datetime;

    public Deadline(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + datetime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
