package main.java;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DeadLine extends Task {
    private LocalDate deadLine;
    private LocalDateTime deadLineWithTime;

    public DeadLine(String description, String deadLine, boolean hasTime) {
        super(description);
        if (!hasTime) {
            this.deadLine = LocalDate.parse(deadLine);
        } else {
            this.deadLineWithTime = LocalDateTime.parse(deadLine);
        }
    }

    @Override
    public String toString() {
        String datePrintFormat;
        if (deadLine != null) {
            datePrintFormat = deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            datePrintFormat = deadLineWithTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + datePrintFormat + ")";
    }
}
