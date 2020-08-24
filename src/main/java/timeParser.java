import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class timeParser {
    
    private LocalDate inputTime;
    
    public timeParser(String inputTime) {
        this.inputTime = LocalDate.parse(inputTime);
    }
    
    public String timeConverter() {
        return inputTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
