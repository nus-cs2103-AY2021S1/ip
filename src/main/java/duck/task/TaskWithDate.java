package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract public class TaskWithDate extends Task {
    private LocalDate date;

    public TaskWithDate(String desc, LocalDate date) {
        super(desc);
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDateAsString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
