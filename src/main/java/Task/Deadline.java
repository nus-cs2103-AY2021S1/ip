package Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDateTime;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        try {
            this.byDateTime = LocalDateTime.parse(reformatedDateTime());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input! Enter appropriate date and time format");
        }
    }

    public String toString() {
        String icon = this.completed ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]";
        if (this.byDateTime == null) {
            return "[D]" + icon + " " + this.description + " (by: " + this.by + ")";
        } else {
            return "[D]" + icon + " " + this.description + " (by: "
                    + this.byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
        }
    }

    public String toEncoding() {
        int completedBinary = this.completed ? 1 : 0;
        return "D>" + completedBinary + ">" + this.description + ">" + this.by;
    }

    private String reformatedDateTime() {
        String[] bySplit = this.by.split(" ", 2);
        String date = bySplit[0];
        String[] dateSplit = date.split("/", 3);
        String time = bySplit[1];
        String hour = time.substring(0, 2);
        String minute = time.substring(2);
        return dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0] + "T" + hour + ":" + minute + ":00";
    }

    public boolean isDate(String date) {
        LocalDate filterDate = LocalDate.parse(date);
        return this.byDateTime.toLocalDate().equals(filterDate);
    }
}
