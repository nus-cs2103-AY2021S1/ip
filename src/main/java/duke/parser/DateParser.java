package duke.parser;

import duke.exceptions.WrongDateFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parse Strings representing date and time.
 */
public class DateParser {

    private static final String format = "dd MMM uuuu HHmm";
    private static final String readableFormat = "DD MMM YYYY HHMM";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

    /**
     * Parse a datetime String into a LocalDateTime object.
     * String should be in the format "DD MMM YYYY hhmm"
     *
     * @param dateTime String representing date and time
     * @return LocalDateTime object for the date and time the String represents
     * @throws WrongDateFormatException if unable to parse the datetime String provided
     */
    public static LocalDateTime parseString(String dateTime) throws WrongDateFormatException {
        try {
            LocalDateTime result = LocalDateTime.parse(dateTime, DateParser.formatter);
            return result;
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException(DateParser.readableFormat);
        }
    }

    /**
     * Parse a ISO datetime format String into a LocalDateTime Object.
     * The ISO datetime format should be of the following structure: "YYYY-MM-DDTHH:MM";
     *
     * @param isoString - ISO datetime format String
     * @return LocalDateTime object for the date and time the isoString represents
     * @throws WrongDateFormatException if unable to parse the ISO datetime format String provided
     */
    public static LocalDateTime parseIso(String isoString) throws WrongDateFormatException {
        try {
            LocalDateTime result = LocalDateTime.parse(isoString);
            return result;
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException("YYYY-MM-DDTHH:MM");
        }
    }

}
