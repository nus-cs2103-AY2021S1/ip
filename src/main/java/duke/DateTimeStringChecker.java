package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class DateTimeStringChecker extends StringChecker {

    public DateTimeStringChecker(String[] userInput) {
        super(userInput);
    }

    private void checkDelimiterPresent(String delimiter) throws DukeException {
        if (Arrays.stream(getStringArray()).noneMatch( x -> x.equals(delimiter))) {
            throw new DukeException("Input command must contain the delimiter " + delimiter
                    + " between task name and date!");
        }
    }

    public DateTime checkDateTime(String delimiter) throws DukeException {
        //Makes sure the delimiter to separate name and date is present
        checkDelimiterPresent(delimiter);
        //Extracts the date and time from the user input string array
        String[] dateTime = extractDateTime(delimiter);
        //Check Date exists and is in the correct format
        LocalDate taskDate = checkDate(dateTime);
        //Verifies any existing time to be in the correct format
        Optional<LocalTime> taskTime = Optional.ofNullable(checkTime(taskDate, dateTime));
        return new DateTime(taskDate, taskTime);
    }

    private LocalDate checkDate(String[] dateTime) throws DukeException {
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
        } else if ( dateTime.length > 1  && !dateTime[1].equals("")) {
            return checkTimeFormat(taskDate, dateTime);
        } else {
            return null;
        }
    }

    private LocalTime checkTimeFormat(LocalDate taskDate, String[] dateTime) throws DukeException {
        try {
            LocalTime taskTime = LocalTime.parse(dateTime[1],DateTimeFormatter.ofPattern("HHmm"));
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

    private String[] extractDateTime(String delimiter) {
        return Arrays.stream(getStringArray()).dropWhile(e -> !e.equals(delimiter))
                .skip(1).collect(Collectors.joining(" ")).split(" ");
    }
}
