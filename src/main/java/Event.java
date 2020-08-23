import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of Task, which takes in a specific date/time.
 */
public class Event extends Task {
    protected String connector;
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Constructor that creates a new Event object based on stored data or input.
     *
     * @param description describes the event.
     * @param id position of event.
     * @param duration duration of event.
     * @param stored whether it is from stored.txt or a new input.
     */
    Event(String description, int id, String duration, boolean stored) {
        super(description, id);
        if (stored) { //stored data
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MMM/yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");
            String[] dateTime = duration.split(" ");
            this.connector = dateTime[0];
            String date = dateTime[1] + "/" + dateTime[2] + "/" + dateTime[3].substring(0, dateTime[3].length() - 1);
            this.date = LocalDate.parse(date, dateFormat);

            this.startTime = LocalTime.parse(dateTime[4], timeFormat);
            this.endTime = LocalTime.parse(dateTime[6], timeFormat);
        } else {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            String[] dateTime = duration.split(" ");
            this.connector = dateTime[0];

            this.date = LocalDate.parse(dateTime[1], dateFormat);

            String[] times = dateTime[2].split("-");
            this.startTime = LocalTime.parse(times[0], timeFormat);
            this.endTime = LocalTime.parse(times[1], timeFormat);
        }
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description + "(" +
                this.connector + " " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) +
                ", " + this.startTime.format(DateTimeFormatter.ofPattern("h:mma")) + " - " +
                this.endTime.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }
}
