import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    private String dueDate;
    private Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    public static Deadline createDeadline(String name, String dueDate) throws DukeException{
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dueDate);
        }
        catch (DateTimeParseException ex) {
            throw new DukeException("Please specify the due date as follows: yyyy-mm-dd");
        }
        return new Deadline(name, localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String missingNameError() {
        return "The description of a deadline task cannot be empty.";
    }

    @Override
    public String toSaveDataFormat() {
        String saveData = "";
        saveData += this.isDone ? 1 : 0;
        saveData += " D " + this.name + "\n" + this.dueDate;
        return saveData;
    }

    @Override
    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        String eventTime = this.dueDate.length() > 0 ? " (by: " + this.dueDate + ")" : "";
        return "[D]" + marked + this.name + eventTime;
    }
}
