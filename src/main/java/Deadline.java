import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a specific type of task of the user
 * that has deadline information stored.
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.type = "D";
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by,inputFormat);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, "D");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by,inputFormat);
    }


    @Override
    public String toString() {
        String byTime = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return super.toString() + "(by: " + byTime + ")"
                + (this.tag == null ? "" : (" <" + this.tag + ">"));

    }
}