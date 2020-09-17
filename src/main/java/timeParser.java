
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a TimeParser and consists of methods related to
 * processs the string that represent the time and convert it to the desired form.
 */
public class TimeParser {

    private String inputTime;

    /**
     * Contructor for a time parser object.
     * @param inputTime input time from the user.
     */
    public TimeParser(String inputTime) {
        this.inputTime = inputTime;
    }

    /**
     * Return the converted time form of the task.
     */
    public String timeConverter() {
        LocalDateTime localDateTime = LocalDateTime.parse(inputTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }
}
