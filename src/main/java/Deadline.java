import java.time.LocalDate;

public class Deadline extends Task {

    // Attributes
    protected LocalDate deadline;

    // Constructor
    public Deadline(String description, LocalDate deadline) throws EmptyBodyException {
        super(description);
        this.deadline = deadline;
    }

    // String Representation
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}