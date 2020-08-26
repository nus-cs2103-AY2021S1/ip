import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;
    
    static {
        format = "deadline <task description> /by <yyyy-mm-dd>";
    }
    
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS! The deadline in an incorrect format! " +
                    "Please indicate the date as <yyyy-mm-dd>");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
