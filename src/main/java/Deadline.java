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

    @Override
    public String toString() {
        String statusIcon = (status)?"✓":"✗";
        return "[" + icon + "]" + "[" + statusIcon + "] "
                + this.taskString + " (" + this.preposition + ": "
                + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
