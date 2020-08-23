package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDateTime date;

    public DeadlineTask(String taskName, LocalDateTime date) {
        super(taskName);
        this.date = date;
    }

    public String getDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
