package duke.parser;

import duke.exceptions.InvalidTimeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * TimeConverter can convert the time in input into the MMM dd yyyy LocalTime format.
 */
public class TimeConverter {
    /**
     * Constructs a time converter.
     *
     * @param time Time command.
     * @return Time in the given format.
     * @throws Exception Throws time excepiton is the command does not meet requirements.
     */
    public String convertTime(String time) throws Exception {
        if (isValidTime(time)) {

            LocalDate localDate = LocalDate.parse(time);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

            String formattedTime = localDate.format(dateTimeFormatter);

            return formattedTime;
        } else {

            throw new InvalidTimeException("I don't understand the due date mentioned in the message. " + "/n" +
                    "Please tell me the time strictly in YYYY-MM-DD format");

        }

    }

    /**
     * Checks whether the time is in the valid format or not.
     *
     * @param time
     * @return
     */
    public boolean isValidTime(String time) {
        String[] timeArray = time.split("-", 3);
        if (timeArray.length == 3) {
            int year = Integer.parseInt(timeArray[0]);
            int month = Integer.parseInt(timeArray[1]);
            int date = Integer.parseInt(timeArray[2]);
            return month <= 12 && date <= 31;
        } else {
            return false;
        }
    }

}


