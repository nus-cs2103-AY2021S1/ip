package duke.task;

import duke.enums.DateTimeFormat;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains information about a Task to be done by a particular date and time along with its description
 */
public class Deadline extends Task {
    private static final String DELIMITER = "by";
    private LocalDate date;
    private LocalTime time;
    
    public Deadline(String description, String dateString) throws DukeException {
        super(description);
        setDateTime(dateString);
    }
    
    /**
     * Creates a Deadline by extracting out relevant information from the parsed user input
     *
     * @param parsedInput Parser's output
     *
     * @return Deadline
     */
    public static Deadline createDeadline(String[] parsedInput) throws DukeException {
        String description = parsedInput[1];
        String dateString = parsedInput[2];
        return new Deadline(description, dateString);
    }
    
    @Override
    public boolean isComplete() {
        return super.isComplete();
    }
    
    @Override
    public String toString() {
        String date = this.date.format(DateTimeFormat.DATE_OUTPUT_FORMATTER1.getFormat());
        String time = this.time.format(DateTimeFormat.TIME_FORMATTER.getFormat());
        return "[D]" + super.toString()
                + " (" + DELIMITER + ":" + date + ", time:" + time + ")";
    }
    
    /**
     * Sets the Date and Time attributes of a Deadline by choosing the correct formatter and formatting the user input
     * information for these fields
     *
     * @param dateString Unformatted date and time fields
     *
     * @throws DukeException If the Date and Time user input is not understandable
     */
    public void setDateTime(String dateString) throws DukeException {
        try {
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
        } catch (Exception e) {
            throw new DukeException("what kinda date/time is that");
            // todo: send this throwing of exception to the parser
        }
    }
    
    /**
     * Filters through the Enumeration for DateTime formatters and selects the correct Formatter based on the format
     * that the user inputs the Date information in
     *
     * @param date String representation for a date
     *
     * @return DateTimeFormatter that can be used to format this user input for Date
     *
     * @throws DukeException If the User's date format isn't supported by the existing Formatters in the Enumeration
     */
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
    
    /**
     * Filters through the Enumeration for DateTime formatters and selects the correct Formatter based on the format
     * that the user inputs the Date information in
     *
     * @param time String representation for a time
     *
     * @return DateTimeFormatter that can be used to format this user input for Time
     *
     * @throws DukeException If the User's time format isn't supported by the existing Formatters in the Enumeration
     */
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
    

