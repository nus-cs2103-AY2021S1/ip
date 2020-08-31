import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends TimedTask {
    public static final String taskIcon = "D";
    public Deadline(String description, String by) {
        super(description, DateTimeUtility.formatString(by));
    }

    @Override
    public String toString() {
        return "["+ this.taskIcon +"]" + super.toString() + " (by: " + super.formatBy() + ")";
    }
}
