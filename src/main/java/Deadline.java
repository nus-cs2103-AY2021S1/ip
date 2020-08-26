import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String deadline) throws EmptyDescriptionException {
        super(deadline.substring(9, deadline.indexOf("/")-1));
        if (description.length() <= 9) {
            throw new EmptyDescriptionException("oops! the description of a deadline cannot be empty");
        }
        this.deadline = LocalDate.parse(deadline.substring(deadline.indexOf("/")+4));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]")
                .append(super.toString())
                .append(" (").append("by: ")
                .append(this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                .append(")");
        return sb.toString();
    }
}
