import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeParser {
    private LocalDate localDate;
    private String time;

    public TimeParser(LocalDate localDate, String time) {
        this.localDate = localDate;
        this.time = time;
    }

    public String getFormattedTime() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            String formattedTime = localDate.format(formatter);
            return formattedTime;
        } catch (Exception e) {
            return time;
        }

    }
}

