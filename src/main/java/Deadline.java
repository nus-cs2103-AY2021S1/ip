import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(boolean isComplete, int index, String instructions, String date) {
        super(isComplete, index, instructions);
        this.date = parseTime(date);
    }

    public Deadline(boolean isComplete, int index, String instructions, LocalDate date) {
        super(isComplete, index, instructions);
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalDate parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(time, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        if (this.isComplete) {
            return "[D][✓] " + this.instructions + " (by: " + outputFormatter.format(this.date) + ")";
        } else {
            return "[D][✗] " + this.instructions + " (by: " + outputFormatter.format(this.date) + ")";
        }
    }
}
