import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime at;
    private String printedFormat;

    public Event(String taskName, String date) throws DukeException {
        super(taskName);
        try {
            this.at = LocalDateTime.parse(
                    date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.printedFormat = this.at.format(
                    DateTimeFormatter.ofPattern("EEE, d MMM yyyy, HH:mm"));
        } catch (DateTimeParseException e) {
            Task.totalTasks--;
            throw new DukeException("You need to use the proper format!\n"
                    + "eg event project meeting /at 2019-10-15 1200");
        }
    }

    @Override
    public String toTaskData() {
        return "E" + " ; " + super.toTaskData() + " ; " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + printedFormat + ")";
    }
}
