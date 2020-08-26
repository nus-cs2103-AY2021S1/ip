import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate date) throws DukeException {
        super(description.substring(8),"deadline");
        this.by = date;
//        this.by = description.substring(description.indexOf("/")+4);
        this.setType("deadline");

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    public String toSave() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}