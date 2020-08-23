import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    protected LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + by.format(DateTimeFormatter.ofPattern("d MMM uuuu"))
                + ")";
    }

}