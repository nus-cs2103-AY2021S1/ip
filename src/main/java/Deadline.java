import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    LocalDate time;

    Deadline(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
