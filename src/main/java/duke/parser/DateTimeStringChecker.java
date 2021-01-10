package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import duke.DateTime;
import duke.DukeException;
import duke.task.TaskType;

/**
 * Ensures that date and time input is in the correct format.
 * Also makes sure that dates are present in user-input.
 */
public class DateTimeStringChecker extends StringChecker {

    public DateTimeStringChecker(String[] userInput) {
        super(userInput);
    }

    private void checkDelimiterPresent(TaskType taskType) throws DukeException {
        if (Arrays.stream(getStringArray()).noneMatch(x -> x.equals(taskType.getDelimiter()))) {
            throw new DukeException("Input command must contain the delimiter " + taskType.getDelimiter()
                    + " between task name and date!");
        }
    }

    /**
     * Checks user-specified date and time for format.
     * Ensures date and times specified are in the future.
     * Also ensures the respective delimiter to separate task name and date is present in user input.
     *
     * @param taskType Type of the task to be checked.
     * @return DateTime object if inputs pass necessary date and time checks.
     * @throws DukeException If date is not present or formatting is incorrect.
     */
    public DateTime checkDateTime(TaskType taskType) throws DukeException {
        //Makes sure the delimiter to separate name and date is present
        checkDelimiterPresent(taskType);
        //Extracts the date and time from the user input string array
        String[] dateTime = extractDateTime(taskType);
        //Check Date exists and is in the correct format
        LocalDate taskDate = checkDate(dateTime);
        //Verifies any existing time to be in the correct format
        Optional<LocalTime> taskTime = Optional.ofNullable(checkTime(taskDate, dateTime));
        return new DateTime(taskDate, taskTime);
    }

    /**
     * Allows date checking by making sure the date exists and its format is correct.
     *
     * @param dateTime Array containing the date/time string.
     * @return The LocalDate object created with the correct date stored.
     * @throws DukeException If date does not exist, or its formatting is incorrect.
     */
    public LocalDate checkDate(String[] dateTime) throws DukeException {
        checkDateExists(dateTime);
        return checkDateFormat(dateTime);
    }

    private void checkDateExists(String[] dateTime) throws DukeException {
        if (super.checkEmptyString(dateTime, 1)) {
            throw new DukeException("The task must come with a date in yyyy-mm-dd format!");
        }
    }

    private LocalDate checkDateFormat(String[] dateTime) throws DukeException {
        try {
            LocalDate taskDate = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //Make sure deadline set is in the future
            LocalDate todayDate = LocalDate.now();
            if (taskDate.isBefore(todayDate)) {
                throw new DukeException("Date for deadline/event tasks must be set in the future!");
            }
            return taskDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("Check your date! All tasks' date must be in yyyy-MM-dd "
                    + "format (i.e. 2021-10-05)!");
        }

    }

    private LocalTime checkTime(LocalDate taskDate, String[] dateTime) throws DukeException {
        if (dateTime.length > 2) {
            throw new DukeException("Please make sure date is imputed in yyyy-mm-dd format. Any optional time"
                    + " parameter should be in HHmm format. Don't add any more characters after the date and time!");
        } else if (dateTime.length > 1 && !dateTime[1].equals("")) {
            return checkTimeFormat(taskDate, dateTime);
        } else {
            return null;
        }
    }

    private LocalTime checkTimeFormat(LocalDate taskDate, String[] dateTime) throws DukeException {
        try {
            LocalTime taskTime = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm"));
            //Make sure time has not passed
            if (taskDate.isEqual(LocalDate.now()) && taskTime.isBefore(LocalTime.now())) {
                throw new DukeException("Check your time! The date and time combination you specified has already "
                        + "passed!");
            }
            return taskTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Check your time! Any time specified must be in HHmm format (i.e. 1800)!");
        }
    }

    private String[] extractDateTime(TaskType taskType) {
        return Arrays.stream(getStringArray()).dropWhile(e -> !e.equals(taskType.getDelimiter()))
                .skip(1).collect(Collectors.joining(" ")).split(" ");
    }
}
