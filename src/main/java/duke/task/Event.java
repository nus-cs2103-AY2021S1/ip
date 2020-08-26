package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime taskDateTime;

    public Event(String desc, LocalDateTime taskDateTime) {
        super(desc);
        this.taskDateTime = taskDateTime;

        if (this.desc.isBlank() || this.taskDateTime == null) {
            throw new DukeException("The description or date of \"event\" cannot be empty");
        }
    }

    public String getSaveToFileString() {
        return "E`" + super.getSaveToFileString() + "`" +
                taskDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                taskDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
