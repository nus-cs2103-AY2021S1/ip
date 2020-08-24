import java.time.LocalDate;

public class Deadline extends Task {

    String by;

    public Deadline(String description, LocalDate date) {
        super(description, date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() + ")\n";
    }
}