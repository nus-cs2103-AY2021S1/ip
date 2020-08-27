import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    private String printedFormat;

    public Deadline(String taskName, String date) throws DukeException {
        super(taskName);
        try {
            this.by = LocalDateTime.parse(
                    date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.printedFormat = this.by.format(
                    DateTimeFormatter.ofPattern("EEE, d MMM yyyy, HH:mm"));
        } catch (DateTimeParseException e) {
            Task.totalTasks--;
            throw new DukeException("You need to use the proper format!\n"
                    + "eg deadline return book /by 2019-10-15 2359");
        }
    }

    @Override
    public String toTaskData() {
        return "D" + " ; " + super.toTaskData() + " ; " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printedFormat + ")";
    }
}
