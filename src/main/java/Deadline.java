import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String connector;
    protected LocalDate date;
    protected LocalTime time;
    protected boolean hasTime;

    Deadline(String description, int id, String dueDateTime) {
        super(description, id);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
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

    @Override
    public String toString() {
        if (hasTime) {
            return "   [D][" + this.getStatusIcon() + "] " + this.description + "(" +
                    this.connector + " " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) +
                    ", " + this.time.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
        } else {
            return "   [D][" + this.getStatusIcon() + "] " + this.description + "(" +
                    this.connector + " " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        }
    }
}
