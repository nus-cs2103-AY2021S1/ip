import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline extends Task {

    protected String by;
    protected LocalDate deadlineDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.deadlineDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            //do nothing
        }
    }

    public void printTime() {
        try {
            System.out.println(this.deadlineDate.getMonth().toString()
            + " " + this.deadlineDate.getDayOfMonth()
            + " " + this.deadlineDate.getYear());
        } catch (NullPointerException e) {
            System.out.println("No valid date available.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
