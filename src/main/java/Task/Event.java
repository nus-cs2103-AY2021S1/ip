package Task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDateTime atDateTime;

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
        try {
            this.atDateTime = LocalDateTime.parse(reformatedDateTime());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input! Enter appropriate date and time format");
        }
    }

    public String toString() {
        String icon = this.completed ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]";
        if (atDateTime == null) {
            return "[E]" + icon + " " + this.description + " (at: " + this.at + ")";
        } else {
            return "[E]" + icon + " " + this.description + " (at: "
                    + this.atDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
        }
    }

    public String toEncoding() {
        int completedBinary = this.completed ? 1 : 0;
        return "E>" + completedBinary + ">" + this.description + ">" + this.at;
    }

    private String reformatedDateTime() {
        String[] bySplit = this.at.split(" ", 2);
        String date = bySplit[0];
        String[] dateSplit = date.split("/", 3);
        String time = bySplit[1];
        String hour = time.substring(0, 2);
        String minute = time.substring(2);
        return dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0] + "T" + hour + ":" + minute + ":00";
    }

    public boolean isDate(LocalDate dateFilter) {
        return this.atDateTime.toLocalDate().equals(dateFilter);
    }
}
