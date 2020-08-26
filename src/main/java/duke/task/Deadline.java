package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime taskBy;

    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.taskBy = by;

        if (this.desc.isBlank() || this.taskBy == null) {
            throw new DukeException("The description or date of \"deadline\" cannot be empty");
        }
    }

    public String getSaveToFileString() {
        return "D`" + super.getSaveToFileString() + "`" +
                taskBy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                taskBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
