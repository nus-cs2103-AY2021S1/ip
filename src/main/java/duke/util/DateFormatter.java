package duke.util;

import static duke.util.Keyword.KEYWORD_ONE_SPACE;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidFormatDateException;

/**
 * Class that helps to format the date of the user's input.
 */
public class DateFormatter {

    public static final DateTimeFormatter FORMAT_DATE_TIME = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");

    /**
     * Format the user's input date and time into a LocalDateTime object.
     *
     * @param dateAndTime The user's input of date and time.
     * @return A LocalDateTime object that contains the information of the timing.
     * @throws InvalidFormatDateException If the inputted user's timing is not of the correct format:
     * YYYY-MM-DD HHMM or YYYY-MM-DD, an exception will be thrown to notify the user.
     */
    public static LocalDateTime formatDateTime(String dateAndTime) throws InvalidFormatDateException {
        assert dateAndTime != null;
        String[] dateFormat = dateAndTime.split(KEYWORD_ONE_SPACE, 2);
        String[] date = dateFormat[0].split("-");
        String time;
        if (dateFormat.length == 1) {
            time = "2359";
            if (date.length != 3) {
                throw new InvalidFormatDateException();
            }
        } else {
            time = dateFormat[1];
            if (date.length != 3 || time.length() != 4) {
                throw new InvalidFormatDateException();
            }
        }
        try {
            assert date != null;
            assert time != null;
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2));
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException | NumberFormatException e) {
            throw new InvalidFormatDateException();
        }
    }
}
