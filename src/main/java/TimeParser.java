import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeParser {
    private LocalDate localDate;

    public TimeParser(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getFormattedTime() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            String formattedTime = localDate.format(formatter);
            return formattedTime;
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}

