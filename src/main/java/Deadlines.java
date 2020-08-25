import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private String by;
    private LocalDateTime localDateTime;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");

    Deadlines(String taskInfo , String by) {
        super(taskInfo, TaskType.DEADLINE);
        this.by = by;
        try {
            this.localDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));;
        }
        catch (DateTimeParseException e) {
            throw new DukeDateTimeParseException("Please input the date and time in yyyy-MM-dd HHmm format with the correct values\n\teg. 2014-12-25 1630");
        }

    }

    public String returnTime() {
        return this.by;
    }

    @Override
    public Deadlines doneTask() {
        super.done = true;
        return this;
    }
    
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), dateTimeFormatter.format(this.localDateTime));
    }
}
