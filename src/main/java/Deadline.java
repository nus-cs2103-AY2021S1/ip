import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task  {
    private LocalDate date;

    Deadline(String name, String dateString) {
        super(name);
        this.date = LocalDate.parse(dateString);
    }

    Deadline(String name, String date, boolean isCompleted) {
        super(name, isCompleted);
        this.date = date;
    }

    public static Deadline create(String description) throws DukeException {
        String[] keywords = description.split(" /by ", 2);
        if (keywords.length < 2) {
            throw new DukeException("☹ OOPS!!! Add a date using \" /by <date>\".\n");
        }
        return new Deadline(keywords[0], keywords[1]);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + date;
    }
}
