import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String connector;
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    Event(String description, int id, String duration) {
        super(description, id);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
        String[] dateTime = duration.split(" ");
        this.connector = dateTime[0];

        this.date = LocalDate.parse(dateTime[1], dateFormat);

        String[] times = dateTime[2].split("-");
        this.startTime = LocalTime.parse(times[0], timeFormat);
        this.endTime = LocalTime.parse(times[1], timeFormat);
    }

    @Override
    public String toString() {
        return "   [E][" + this.getStatusIcon() + "] " + this.description + "(" +
                this.connector + " " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) +
                ", " + this.startTime.format(DateTimeFormatter.ofPattern("h:mma")) + " - " +
                this.endTime.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }
}
