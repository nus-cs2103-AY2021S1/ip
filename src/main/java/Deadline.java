import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {
    private static final String DELIMITER = "by";
    private LocalDate date;
    private LocalTime time;
    
    public Deadline(String description, String dateString) throws DukeException {
        super(description);
        setDateTime(dateString);
    }
    
    // example: deadline return book /by Sunday
    public static Deadline createDeadline(String[] parsedOutput) throws DukeException {
        String description = parsedOutput[1];
        String dateString = parsedOutput[2];
        return new Deadline(description, dateString);
    }
    
    @Override
    protected boolean isComplete() {
        return super.isComplete();
    }
    
    @Override
    public String toString() {
        String date = this.date.format(DateTimeFormat.DATE_OUTPUT_FORMATTER1.getFormat());
        String time = this.time.format(DateTimeFormat.TIME_FORMATTER.getFormat());
        return "[D]" + super.toString()
                + " (" + DELIMITER + ":" + date + ", time:" + time + ")";
    }
    
    public void setDateTime(String dateString) throws DukeException {
        dateString = dateString.strip();
        String[] dateTimeSeparated = dateString.split(" ");
        String date = dateTimeSeparated[0];
        String time = dateTimeSeparated[1];
        
        DateTimeFormatter chosenDateFormatter = chooseDateFormatter(date);
        DateTimeFormatter chosenTimeFormatter = chooseTimeFormatter(time);
        LocalDate localDate = LocalDate.parse(date, chosenDateFormatter);
        LocalTime localTime = LocalTime.parse(time, chosenTimeFormatter);
        this.date = localDate;
        this.time = localTime;
    }
    
    private DateTimeFormatter chooseDateFormatter(String date) throws DukeException {
        return DateTimeFormat.getFormatterStream().filter
                (formatter -> {
                    try {
                        LocalDate.parse(date, formatter);
                        return true;
                    } catch (DateTimeParseException ignored) {
                        // ON PURPOSE: ignored because we only want the formatter that handles things properly
                    }
                    return false;
                }).findAny().orElseThrow(() -> new DukeException("Can't find Date Formatter"));
    }
    
    private DateTimeFormatter chooseTimeFormatter(String time) throws DukeException {
        return DateTimeFormat.getFormatterStream().filter
                (formatter -> {
                    try {
                        LocalTime.parse(time, formatter);
                        return true;
                    } catch (Exception ignored) {
                        // ON PURPOSE: ignored because we only want the formatter that handles things properly
                    }
                    return false;
                }).findAny().orElseThrow(() -> new DukeException("Can't find Time Formatter"));
    }
}
    

