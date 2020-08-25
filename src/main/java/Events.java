import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private String at;
    private LocalDateTime localDateTime;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");

    Events(String taskInfo , String at) {
        super(taskInfo, TaskType.EVENT);
        this.at = at;
        try {
            this.localDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));;
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParseException("Please input the date and time in yyyy-MM-dd HHmm format with the correct values\n\teg. 2014-12-25 1630");
        }

    }

    public String returnTime() {
        return this.at;
    }

    @Override
    public Events doneTask() {
        super.done = true;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), dateTimeFormatter.format(this.localDateTime));
    }

}
