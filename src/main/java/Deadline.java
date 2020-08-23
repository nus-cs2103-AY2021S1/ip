import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String connector;
    protected LocalDate date;
    protected LocalTime time;
    protected boolean hasTime;

    Deadline(String description, int id, String dueDateTime, boolean stored) {
        super(description, id);
        if (stored) { // stored data
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MMM/yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");
            String[] dateTime = dueDateTime.split(" ");
            this.connector = dateTime[0];
            if (dateTime.length > 4) { // if user inputs time after date
                String date = dateTime[1] + "/" + dateTime[2] + "/" + dateTime[3].substring(0, dateTime[3].length() - 1);
                this.date = LocalDate.parse(date, dateFormat);
                this.time = LocalTime.parse(dateTime[4], timeFormat);
                this.hasTime = true;
            } else {
                String date = dateTime[1] + "/" + dateTime[2] + "/" + dateTime[3];
                this.date = LocalDate.parse(date, dateFormat);
                this.hasTime = false;
            }
        } else {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            String[] dateTime = dueDateTime.split(" ");
            this.connector = dateTime[0];
            this.date = LocalDate.parse(dateTime[1], dateFormat);
            if (dateTime.length == 3) { // if user inputs time after date
                this.time = LocalTime.parse(dateTime[2], timeFormat);
                this.hasTime = true;
            } else {
                this.hasTime = false;
            }
        }
    }

    @Override
    public String toString() {
        if (hasTime) {
            return "[D][" + this.getStatusIcon() + "] " + this.description + "(" +
                    this.connector + " " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) +
                    ", " + this.time.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
        } else {
            return "[D][" + this.getStatusIcon() + "] " + this.description + "(" +
                    this.connector + " " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        }
    }
}
