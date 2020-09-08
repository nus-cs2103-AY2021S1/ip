package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {
    private LocalDate deadLine;
    private LocalDateTime deadLineWithTime;

    public DeadLine(
            String description, String time, boolean hasTime, boolean isDone, String priority) {
        super(description, isDone, priority);
        if (!hasTime) {
            this.deadLine = LocalDate.parse(time);
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.deadLineWithTime = LocalDateTime.parse(time, format);
        }
    }


    @Override
    public String getStorageString() {
        return "D | " + this.getStatusIcon() + " | " + this.description
                + " | " + this.deadLine + " | " + this.priority.toString() +"\n";
    }

    @Override
    public String toString() {
        String datePrintFormat;
        if (deadLine != null) {
            datePrintFormat = deadLine.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            datePrintFormat = deadLineWithTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return "[D][" + this.getStatusIcon() + "] "
                + this.description + " (by: " + datePrintFormat + ")"
                + " (Priority: " + this.priority.toString() + ")";
    }
}
