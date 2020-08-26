import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskDate {
    private LocalDate date;
    private String time;

    TaskDate(LocalDate date, String time) {
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy " + time));
    }
}
