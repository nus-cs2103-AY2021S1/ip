
package duke.task;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String deadlineDate;
    private LocalDate deadlineLocalDate;
    private String formatDeadlineDate;

    public Deadline(String taskName, String deadlineDate) throws DukeException {
        super(taskName, "D");
        try {
            this.deadlineDate = deadlineDate;
            this.deadlineLocalDate = LocalDate.parse(deadlineDate);
            this.formatDeadlineDate = this.deadlineLocalDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (Exception e) {
            throw new DukeException("Please use YYYY-MM-DD format for dates~");
        }
    }
    
    public String getDeadlineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        String check;
        if (done == true) {
            check = "✓";
        } else {
            check = "✗";
        }
        return "[" + taskType + "][" + check + "] " + taskName + " (by:" + formatDeadlineDate + ")";
    }
}

