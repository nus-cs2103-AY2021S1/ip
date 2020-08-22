import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class TimedTask extends Task {
    protected LocalDateTime datetime;
    protected static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy kk:mm");
    protected static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("d MMM yyyy hh.mma");

    protected TimedTask(String description, String datetime) {
        super(description);
        this.datetime = LocalDateTime.parse(datetime, TimedTask.inputFormatter);
    }

    protected String datetimeString() {
        return this.datetime.format(TimedTask.printFormatter);
    }

    protected LocalDate getDate() {
        return this.datetime.toLocalDate();
    }

}
