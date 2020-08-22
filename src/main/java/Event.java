import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime dateTime;

    Event(String task, LocalDateTime dateTime) {
        super(task);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        int dd = this.dateTime.getDayOfMonth();
        int mm = this.dateTime.getMonthValue();
        int yyyy = this.dateTime.getYear();
        int hour = this.dateTime.getHour();
        int min = this.dateTime.getMinute();
        return dd + " " + mm + " " + yyyy + " " + hour + " " + min;
    }

    public String toString() {
        String MMM = this.dateTime.getMonth().toString().substring(0, 3);
        String DDD = this.dateTime.getDayOfWeek().toString().substring(0, 3);
        int dd = this.dateTime.getDayOfMonth();
        int yyyy = this.dateTime.getYear();
        String HHHH = this.dateTime.toLocalTime().toString();
        return "[E]" + super.toString() + "(at: " + dd + " " + MMM + " " + yyyy + ", " + DDD + " @ " + HHHH + ")";
    }
}