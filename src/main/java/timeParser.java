
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class timeParser {

    private String inputTime;

    public timeParser(String inputTime) {
        this.inputTime = inputTime;
    }

    public String timeConverter() {
        LocalDateTime localDateTime = LocalDateTime.parse(inputTime,DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }
}
