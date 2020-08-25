import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String type = "D";
    private String by;
    private LocalDate date;
    private String time = "";

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        // In case time is included
        String dateAndTime[] = by.split("\\s");
        this.date = LocalDate.parse(dateAndTime[0]);
        if (dateAndTime.length > 1) {
            // If time is given as well
            this.time = dateAndTime[1].trim();
        }
    }

    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + " " + this.time + ")";
    }
}
