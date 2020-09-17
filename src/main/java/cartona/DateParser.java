package cartona;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import cartona.exception.InvalidTaskTimeException;
import cartona.task.TaskDate;


/**
 * The DateParser class is a group of static methods used to parse dates.
 *
 * @author Jaya Rengam
 */
public class DateParser {
    /**
     * Parses the TaskDate from a user's input.
     * @return A TaskDate representing the date and time.
     * @throws InvalidTaskTimeException if there is an error in formatting of the date or time.
     */
    public static TaskDate parseTaskDate(String dateString) throws InvalidTaskTimeException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateString,
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));

            return new TaskDate(dateTime);
        } catch (DateTimeException e) {
            throw new InvalidTaskTimeException("Date/time formatting error: " + e.getMessage());
        }
    }

    /**
     * Parses the LocalDate from a user's input.
     *
     * @return the LocalDate
     */

    /**
     * Parses the date of a new Event input string to get the start or end date of the event
     * @param dateString The input string.
     * @param startOrEnd Value representing which date to get:
     *                       true to get the start date and false to get the end date.
     * @return The start/end date of the event represented by a TaskDate
     * @throws InvalidTaskTimeException if there is an error in the formatting of the date or time.
     */
    public static TaskDate getRange(String dateString, boolean startOrEnd) throws InvalidTaskTimeException {
        String[] dateAndTimes = dateString.split(" ");
        String[] dates = dateAndTimes[0].split("/");

        int year = Integer.valueOf(dates[0]);
        int month = Integer.valueOf(dates[1]);
        int day = Integer.valueOf(dates[2]);

        try {
            if (startOrEnd) {
                return new TaskDate(LocalDate.of(year, month, day)
                                    .atTime(LocalTime.parse(dateAndTimes[1],
                                                            DateTimeFormatter.ofPattern("HHmm"))));
            } else {
                return new TaskDate(LocalDate.of(year, month, day)
                                    .atTime(LocalTime.parse(dateAndTimes[2],
                                            DateTimeFormatter.ofPattern("HHmm"))));
            }
        } catch (DateTimeException e) {
            throw new InvalidTaskTimeException("Date/time formatting error: " + e.getMessage());
        }
    }

    /**
     * Parses the date of a new Event input string from a text file to get the start or end date of the event.
     * @param storedString The stored string.
     * @param startOrEnd Value representing which date to get:
     *                       true to get the start date and false to get the end date.
     * @return The start/end date of the event represented by a TaskDate
     */
    public static TaskDate parseRangeFromStorage(String storedString, boolean startOrEnd) {
        String[] dateAndTime = storedString.split(" - ");;

        if (startOrEnd) {
            return new TaskDate(LocalDateTime.parse(dateAndTime[0],
                    DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
        } else {
            return new TaskDate(LocalDate.parse(dateAndTime[0],
                    DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                            .atTime(LocalTime.parse(dateAndTime[1],
                                        DateTimeFormatter.ofPattern("HHmm"))));
        }
    }

    /**
     * Parses the date from a line of text in the storage file.
     * @return The date represented by a TaskDate
     * @throws InvalidTaskTimeException if there is an error in formatting of the date or time.
     */
    public static TaskDate parseDateFromStorage(String storedString) {

        LocalDateTime dateTime = LocalDateTime.parse(storedString,
                                                     DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

        return new TaskDate(dateTime);
    }
}
