import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String deadline) throws EmptyDescriptionException {
        super(deadline.substring(9, deadline.indexOf("/")-1));
        if (description.length() <= 9) {
            throw new EmptyDescriptionException("oops! the description of a deadline cannot be empty");
        }
        this.deadline = deadline.substring(deadline.indexOf("/")+4);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        String deadline;
        try {
            LocalDate localDate = LocalDate.parse(this.deadline);
            deadline = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            deadline = this.deadline;
        }

        sb.append("[D]")
                .append(super.toString())
                .append(" (").append("by: ")
                .append(deadline)
                .append(")");
        return sb.toString();
    }
}
