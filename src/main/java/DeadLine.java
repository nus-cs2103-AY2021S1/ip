package main.java;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DeadLine extends Task {
    private LocalDate deadLine;
    private LocalDateTime deadLineWithTime;

    public DeadLine(String description, String deadLine, boolean hasTime, boolean isDone) {
        super(description, isDone);
        if (!hasTime) {
            this.deadLine = LocalDate.parse(deadLine);
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.deadLineWithTime = LocalDateTime.parse(deadLine, format);
        }
    }


    @Override
    public String getStorageString() {
        return "D | " + this.getStatusIcon() + " | " + this.description
                + " | " + this.deadLine + "\n";
    }

    @Override
    public String toString() {
        String datePrintFormat;
        if (deadLine != null) {
            datePrintFormat = deadLine.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            datePrintFormat = deadLineWithTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + datePrintFormat + ")";
    }
}
