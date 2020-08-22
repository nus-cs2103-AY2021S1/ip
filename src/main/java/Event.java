import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class.
 * Represents a event task.
 * Extends from Task.
 *
 * @author YanCheng
 */
public class Event extends Task {

    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

    /**
     * Constructor for Deadline.
     * @param taskName name of deadline
     * @param date date of deadline
     */
    public Event(String taskName, String date) {
        // date format must be in YYYY-MM-DD e.g. 2020-08-22
        // time format must by in XX:XX-YY:YY e.g. 14:00-16:00
        super(taskName.stripTrailing());
        String[] arr = date.split(" ");
        String eventDate = arr[0];
        String[] eventTime = arr[1].split("-");
        String eventStartTime = eventTime[0];
        String eventEndTime = eventTime[1];
        try {
            this.date = LocalDate.parse(eventDate);
            this.startTime = LocalTime.parse(eventStartTime);
            this.endTime = LocalTime.parse(eventEndTime);
        } catch (DateTimeParseException e) {
            System.out.println("Date and time is in the wrong format");
        }
    }

    /**
     * Return deadline information to be stored locally.
     * @return deadline information
     */
    @Override
    public String getInfo() {
        return String.format("E | %d | %s | %s %s-%s\n", isDone ? 1 : 0,
                taskName, date.toString(), startTime.toString(), endTime.toString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + startTime + " - " + endTime + ")";
    }

}
