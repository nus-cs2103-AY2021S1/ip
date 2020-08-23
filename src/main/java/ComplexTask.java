import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ComplexTask extends Task{

    private String at;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime dateAndTime;

    protected ComplexTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    protected ComplexTask(String description, String at) {
        super(description);
        this.at = at;
    }

    protected ComplexTask(String description, LocalDateTime dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    protected ComplexTask(String description, LocalTime time) {
        super(description);
        this.time = time;
    }

    private String getTimeFormat() {
        if (at != null) {
            return at;
        } else if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else if (time != null) {
            return time.format(DateTimeFormatter.ofPattern("hh.mm a"));
        } else {
            return dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh.mm a"));
        }
    }

    protected String convertToString(TaskType taskType) {
        if (taskType == TaskType.EVENT) {
            return "[E]" + super.toString() + " (at: "
                    + getTimeFormat()
                    + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + getTimeFormat()
                    + ")";
        }
    }
}
