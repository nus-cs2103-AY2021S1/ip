import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate time;

    public Deadline(String name, LocalDate time, boolean status) {
        super(name, status);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.time + ")";
    }

    @Override
    public LocalDate getTime() {
        return this.time;
    }

    @Override
    public String getType() {
        return "D";
    }
}
