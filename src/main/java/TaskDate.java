import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TaskDate {
    private LocalDateTime dateTime;

    TaskDate(LocalDate date, String time) {
        this.dateTime = date.atTime(LocalTime.parse(time,
                                    DateTimeFormatter.ofPattern("HHmm")));
    }

    TaskDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    public String getTime() {
        return this.dateTime.toLocalTime().format(
                             DateTimeFormatter.ofPattern("HHmm"));
    }

    public String getStorageString() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
    }

    @Override
    public String toString() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}
