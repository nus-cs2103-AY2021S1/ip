import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    LocalDate date;

    public Deadline(String taskName, String date) {
        // date format must be in YYYY-MM-DD e.g. 2020-08-22
        super(taskName.stripTrailing());
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            System.out.println("Date is in the wrong format");
        }
    }

    @Override
    public String getInfo() {
        return String.format("D | %d | %s | %s\n", isDone ? 1 : 0, taskName, date.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
