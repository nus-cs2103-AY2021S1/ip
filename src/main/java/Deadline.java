import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS! The deadline in an incorrect format! " +
                    "Please indicate the date as <yyyy-mm-dd>");
        }
    }

    public String getParsedTask() {
        return "deadline " + this.description + " /by " + this.by + "\n" 
                + this.done + "\n";
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
