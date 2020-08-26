package duke.command;

import duke.exception.InvalidTaskDateException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Processes dates and time found in user commands and converts it into something that the program 
 * can understand.
 */
public class TaskDate {
    /**
     * Processes the date of and time indicated in user commands and converts it into another form.
     * Input date is of the form "yyyy-MM-dd" and is converted into "dd MMM yyyy" form.
     * Input time follows the 24-hour clock system and of the form "HH:mm". It is converted into 
     * "hh:mm aa" form, which follows the 12-hour clock. The day of the week is returned along with
     * the converted date and time.
     *
     * For example, given input date and time "2020-01-01 18:00", "1 Jan 2020, Wednesday 06:00 PM" 
     * would be returned.
     *
     * @param userInput Date and time of deadline or event.
     * @return Date of form "d MMM yyyy", time of form "hh:mm aa" (12-hour clock) and day of the week.
     * @throws InvalidTaskDateException If input date and time do not match the format or are invalid.
     */
    protected static String getDate(String userInput) throws InvalidTaskDateException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMM yyyy, EEEE hh:mm a");
        String taskDateOutput = "";

        try {
            LocalDateTime taskDate = LocalDateTime.parse(userInput, inputFormat);
            taskDateOutput = taskDate.format(outputFormat);

            // If input date and time do not match the format or are invalid
        } catch (DateTimeException e) {
            throw new InvalidTaskDateException(userInput);
        }

        return taskDateOutput;
    }
}
