package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime taskDateTime;

    public Event(String desc, LocalDateTime taskDateTime) {
        super(desc);
        this.taskDateTime = taskDateTime;
    }

    public String getSaveToFileString() {
        return "E`" + super.getSaveToFileString() + "`" + taskDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.taskDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
