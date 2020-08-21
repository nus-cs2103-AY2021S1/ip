import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate byTime;

    public Deadline(String description, String byTime) throws InvalidCommandException {
        super(description);
        try {
            this.byTime = LocalDate.parse(byTime, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid input date: " + e.getMessage() + ", please input as yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTime + ")";
    }
}
