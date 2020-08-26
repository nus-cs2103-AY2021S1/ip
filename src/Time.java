import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Time {
    private LocalDate localDate;


    public Time(String time) {
        localDate = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM_d_yyyy"));
    }
}
