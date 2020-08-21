import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate byTime;

    public Deadline(String description, String byTime) throws InvalidCommandException {
        super(description);
        try {
            this.byTime = LocalDate.parse(byTime);
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid input date: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTime + ")";
    }
}
