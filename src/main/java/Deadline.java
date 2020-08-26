import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    //protected String by;
    protected LocalDate byDate;
    protected LocalTime byTime;

    public Deadline(String description, String by) {
        super(description);
        this.byDate = LocalDate.parse(by.split(" ")[0]);
        this.byTime = LocalTime.parse(by.split(" ")[1]);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("ha");
        return "[D]" + super.toString() + " (by: "
                + dateFormatter.format(byDate) + " "
                + timeFormatter.format(byTime) + ")";
    }

    @Override
    public String toStringFileFormat() {
        return "[D]" + super.toStringFileFormat() + " (by: " + byDate + " " + byTime + ")";
    }
}
