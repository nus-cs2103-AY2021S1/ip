import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    public static String icon = "D";
    private String preposition;
    private LocalDateTime dateTime;


    Deadline(String taskString, String preposition, String dateTimeString) {
        super(taskString);
        this.preposition = preposition;
        this.dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    Deadline(String taskString, String preposition, String deadlineString, boolean status) {
        super(taskString);
        this.preposition = preposition;
        this.deadlineString = deadlineString;
        this.status = status;
    }

    @Override
    public String toString() {
        String statusIcon = (status)?"✓":"✗";
        return "[" + icon + "]" + "[" + statusIcon + "] "
                + this.taskString + " (" + this.preposition + ": "
                + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    @Override
    public String toDataString() {
        return "deadline//" + taskString + "//" + preposition + "//" + deadlineString + "//" + status;
    }
}
