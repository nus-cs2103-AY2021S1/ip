import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    private String dueDate;
    private String dueDateInSaveFormat;

    private Deadline(String name, String dueDate, String dueDateInSaveFormat) {
        super(name);
        this.dueDate = dueDate;
        this.dueDateInSaveFormat = dueDateInSaveFormat;
    }

    /**
     * Creates a Deadline instance.
     * @param name Name of the Deadline task.
     * @param dueDate Due date of the Deadline task.
     * @return A new Deadline instance.
     * @throws DukeException If dueDate is in the wrong format (correct format: YYYY-MM-DD).
     */
    public static Deadline createDeadline(String name, String dueDate) throws DukeException{
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dueDate);
        }
        catch (DateTimeParseException ex) {
            throw new DukeException("Please specify the due date as follows: yyyy-mm-dd");
        }
        return new Deadline(name, localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")), dueDate);
    }

    /**
     * Returns the error message for the case where the name of the
     * Deadline task is empty.
     * @return Error message for missing name.
     */
    @Override
    public String returnMissingNameError() {
        return "The name of a deadline task cannot be empty.";
    }

    /**
     * Returns the Deadline instance's Save Data String used by the Storage class when writing to
     * duke data text file on disk.
     * @see Storage#saveTasksToDisk(TaskList)
     * @return The Save Data String of the Deadline instance.
     */
    @Override
    public String getSaveDataString() {
        String saveData = "";
        saveData += this.isDone ? 1 : 0;
        saveData += " D " + this.name + "\n" + this.dueDateInSaveFormat;
        return saveData;
    }

    /**
     * Returns String representation of the Deadline instance.
     * @return String representation of the Deadline instance.
     */
    @Override
    public String toString() {
        String marked = this.isDone ? "[Y] " : "[N] ";
//        String marked = this.isDone ? "[✓] " : "[✗] ";
        String eventTime = this.dueDate.length() > 0 ? " (by: " + this.dueDate + ")" : "";
        return "[D]" + marked + this.name + eventTime;
    }
}
