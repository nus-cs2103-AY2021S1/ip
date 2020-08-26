package duke.task;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected LocalTime time;
    protected boolean isFormatted = false;

    public Deadline(String description, String by) {
        super(description);
        try {
            String[] arr = by.split(" ");
            this.date = LocalDate.parse(arr[0]);
            this.time = LocalTime.parse(arr[1]);
            this.isFormatted = true;
        } catch (DateTimeException e) {
            System.out.println("Date format wrong! eg. 2020-12-12 18:00");
            this.by = by;
        }
    }

    @Override
    public String toString() {
        if (isFormatted) {
            return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " +this.time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }

    }
}
