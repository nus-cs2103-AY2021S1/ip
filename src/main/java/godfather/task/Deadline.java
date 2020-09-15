package godfather.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import godfather.enums.DateTimeFormat;
import godfather.enums.Message;
import godfather.exception.VitoException;

/**
 * Contains information about a Task to be done by a particular date and time along with its description
 */
public class Deadline extends Task {
    private static final String DELIMITER = "by";
    private LocalDate date;
    private LocalTime time;
    /**
     * Constructs the Deadline object
     *
     * @param description String description attribute for Deadline
     * @param dateString  String representation for the date
     * @param newTaskID   Task number to assign to the new deadline task
     *
     * @throws godfather.exception.VitoException If there are issues writing onto the savedData upon adding
     */
    public Deadline(String description, String dateString, int newTaskID) throws VitoException {
        super(description, newTaskID);
        setDateTime(dateString);
    }
    /**
     * Creates a Deadline by extracting out relevant information from the parsed user input
     *
     * @param parsedInput Parser's output
     *
     * @return Deadline
     */
    public static Deadline createDeadline(String[] parsedInput, int newTaskID) throws VitoException {
        String description = parsedInput[1];
        String dateString = parsedInput[2];
        return new Deadline(description, dateString, newTaskID);
    }
    @Override
    public boolean isComplete() {
        return super.isComplete();
    }
    @Override
    public String toString() {
        String date = this.date.format(DateTimeFormat.DATE_OUTPUT_FORMATTER1.getFormat());
        String time = this.time.format(DateTimeFormat.TIME_FORMATTER.getFormat());
        return "[D]" + super.toString() + " (" + DELIMITER + ":" + date + ", time:" + time + ")";
    }
    /**
     * Sets the Date and Time attributes of a Deadline by choosing the correct formatter and formatting the user input
     * information for these fields
     *
     * @param dateTimeString Unformatted date and time fields
     *
     * @throws godfather.exception.VitoException If the Date and Time user input is not understandable
     */
    public void setDateTime(String dateTimeString) throws VitoException {
        try {
            dateTimeString = dateTimeString.strip();
            String[] dateTimeSeparated = dateTimeString.split(" ");
            String dateString = dateTimeSeparated[0];
            String timeString = dateTimeSeparated[1];
            DateTimeFormatter chosenDateFormatter = chooseDateFormatter(dateString);
            DateTimeFormatter chosenTimeFormatter = chooseTimeFormatter(timeString);
            LocalDate localDate = LocalDate.parse(dateString, chosenDateFormatter);
            LocalTime localTime = LocalTime.parse(timeString, chosenTimeFormatter);
            this.date = localDate;
            this.time = localTime;
        } catch (Exception e) {
            throw new VitoException(Message.ERROR_BAD_TIME_INPUT.getMsg());
        }
    }
    /**
     * Filters through the Enumeration for DateTime formatters and selects the correct Formatter based on the format
     * that the user inputs the Date information in
     *
     * @param dateString String representation for a date
     *
     * @return DateTimeFormatter that can be used to format this user input for Date
     *
     * @throws godfather.exception.VitoException If the User's date format isn't supported by the existing Formatters in
     *                                           the Enumeration
     */
    private DateTimeFormatter chooseDateFormatter(String dateString) throws VitoException {
        return DateTimeFormat.getFormatterStream().filter(formatter -> {
            // filters through stream of formatters to find one that works:
            try {
                LocalDate.parse(dateString, formatter);
                return true;
            } catch (DateTimeParseException ignored) {
                // ON PURPOSE: ignored because we only want the formatter that handles things properly
            }
            return false;
        }).findAny().orElseThrow(() -> new VitoException(Message.ERROR_NO_DATE_FORMATTER.getMsg()));
    }
    /**
     * Filters through the Enumeration for DateTime formatters and selects the correct Formatter based on the format
     * that the user inputs the Date information in
     *
     * @param timeString String representation for a time
     *
     * @return DateTimeFormatter that can be used to format this user input for Time
     *
     * @throws godfather.exception.VitoException If the User's time format isn't supported by the existing Formatters in
     *                                           the Enumeration
     */
    private DateTimeFormatter chooseTimeFormatter(String timeString) throws VitoException {
        return DateTimeFormat.getFormatterStream().filter(formatter -> {
            // filters through stream of formatters to find one that works:
            try {
                LocalTime.parse(timeString, formatter);
                return true;
            } catch (Exception ignored) {
                // ignored because we only want the formatter that handles things properly
            }
            return false;
        }).findAny().orElseThrow(() -> new VitoException(Message.ERROR_NO_TIME_FORMATTER.getMsg()));
    }
}
