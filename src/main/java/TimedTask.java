import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimedTask extends Task {
    protected LocalDateTime dateTime;
    protected String taskType;

    TimedTask(String name, String dateTime) {
        super(name);
        this.dateTime = LocalDateTime.parse(dateTime,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        this.taskType = "TimedTask";
    }

    TimedTask(String name, Boolean isDone, String dateTime) {
        super(name, isDone);
        this.dateTime = LocalDateTime.parse(dateTime,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        this.taskType = "TimedTask";
    }
    
    @Override
    public String encode() {
        String dateTimeFormat = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        return isDone
                ? String.format("%s | 1 | %s | %s", taskType, name, dateTimeFormat)
                : String.format("%s | 0 | %s | %s", taskType, name, dateTimeFormat);
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}