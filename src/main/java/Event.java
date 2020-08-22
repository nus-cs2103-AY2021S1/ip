import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

    public Event(String taskName, String date) {
        super(taskName.stripTrailing());
        String[] arr = date.split(" ");
        String eventDate = arr[0];
        String[] eventTime = arr[1].split("-");
        String eventStartTime = eventTime[0];
        String eventEndTime = eventTime[1];
        this.date = LocalDate.parse(eventDate);
        this.startTime = LocalTime.parse(eventStartTime);
        this.endTime = LocalTime.parse(eventEndTime);
    }

    @Override
    public String getInfo() {
        return String.format("E | %d | %s | %s %s-%s\n", isDone ? 1 : 0, taskName, date.toString(), startTime.toString(), endTime.toString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + " " + startTime + " - " + endTime + ")";
    }

}
