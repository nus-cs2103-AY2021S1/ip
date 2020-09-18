import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    private String timing;
    private String timingInSaveFormat;
    private Event(String name, String timing, String timingInSaveFormat) {
        super(name);
        this.timing = timing;
        this.timingInSaveFormat = timingInSaveFormat;
    }

    /**
     * Creates an Event instance.
     * @param name Name of Event task.
     * @param timing Date of the Event task.
     * @return A new Event instance.
     * @throws DukeException If timing is in the wrong format (correct format: YYYY-MM-DD).
     */
    public static Event createEvent(String name, String timing) throws DukeException {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(timing);
        }
        catch (DateTimeParseException ex) {
            throw new DukeException("Please specify the date as follows: yyyy-mm-dd");
        }
        return new Event(name,
                localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")), timing);
    }

    /**
     * Returns the error message for the case where the name of the
     * Event task is empty.
     * @return Error message for missing name.
     */
    @Override
    public String returnMissingNameError() {
        return "The name of an event task cannot be empty.";
    }

    /**
     * Returns the Event instance's Save Data String used by the Storage class when writing to
     * duke data text file on disk.
     * @see Storage#saveTasksToDisk(TaskList)
     * @return The Save Data String of the Event instance.
     */
    @Override
    public String getSaveDataString() {
        String saveData = "";
        saveData += this.isDone ? 1 : 0;
        saveData += " E " + this.name + "\n" + this.timingInSaveFormat;
        return saveData;
    }

    /**
     * Returns String representation of the Event instance.
     * @return String representation of the Event instance.
     */
    @Override
    public String toString() {
        String marked = this.isDone ? "[Y] " : "[N] ";
//        String marked = this.isDone ? "[✓] " : "[✗] ";
        String eventTime = this.timing.length() > 0 ? " (at: " + this.timing + ")" : "";
        return "[E]" + marked + this.name + eventTime;
    }
}
