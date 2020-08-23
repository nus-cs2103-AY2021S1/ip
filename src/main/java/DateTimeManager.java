import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeManager {
    public static LocalDateTime setDateTime(CharSequence s){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu HHmm");
        LocalDateTime dt = LocalDateTime.parse(s,format);
        return dt;
    }
    public static LocalDate setDate(CharSequence s){
        LocalDate d = LocalDate.parse(s);
        return d;
    }
}
