package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDateTime date;
    public EventTask(String taskName, LocalDateTime time) {
        super(taskName);
        this.date = time;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
