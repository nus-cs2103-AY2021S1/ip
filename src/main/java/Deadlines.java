import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadlines extends Task {
    private LocalDate dueDate;
    private LocalTime dueTime;

    public Deadlines(String description, LocalDate dueDate, LocalTime dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public Deadlines(String description, LocalDate dueDate, LocalTime dueTime, boolean bool) {
        super(description, bool);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    @Override
    public Deadlines markDone() {
        return new Deadlines(this.description, this.dueDate, this.dueTime, true);
    }

    @Override
    public String toString() {
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("E, d MMM yyyy");
        DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("h:m a");
        String formattedDate = this.dueDate.format(myDateFormat);
        String formattedTime =this.dueTime.format(myTimeFormat);
        if (this.isComplete) {
            return "[D][\u2713] " + this.description + "(by:" + formattedDate + ", " +  formattedTime + ")";
        } else {
            return "[D][\u2718] " + this.description + "(by:" + formattedDate + ", " + formattedTime + ")";
        }
    }
}
