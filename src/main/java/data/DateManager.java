package data;

import java.util.Date;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * data.DateManager object parses valid String inputs from Deadline and Event
 * objects to be stored as Date objects.
 *
 * @author Hakiem Rasid
 */
public class DateManager {

    private static String[] DATE_INPUT_FORMATS = {"invalid", "dd-MM-yyyy", "dd/MM/yyyy", "dd-MM-yyyy kkmm",
        "dd/MM/yyyy kkmm"};
    private static String[] DATE_OUTPUT_FORMATS = {"MMM dd yyyy", "MMM dd yyyy',' hh:mma"};

    /**
     * Returns Optional containing Date if String is valid.
     *
     * @param str Input String to be parsed as Date object.
     * @return Optional containing Date if String is valid. Empty Optional otherwise.
     */
    public Optional<Date> getDate(String str) {
        try {
            if (getDateFormat(str).equals(DATE_INPUT_FORMATS[0])) {
                // returns empty Optional if str is not of valid format
                Optional<Date> empty = Optional.empty();
                return empty;
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat(getDateFormat(str));
                return Optional.of(formatter.parse(str));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Returns String representation of valid Date object.
     *
     * @param str Valid input String to be parsed as Date.
     * @return String representation of Date.
     */
    public String getDateAsString(String str) {
        // assumes that str input has valid date format
        // input checks done in Deadline and Event

        SimpleDateFormat sdf;

        if (getDateFormat(str).equals(DATE_INPUT_FORMATS[1]) ||
                getDateFormat(str).equals(DATE_INPUT_FORMATS[2])) {

            sdf = new SimpleDateFormat(DATE_OUTPUT_FORMATS[0]);
        } else {
            sdf = new SimpleDateFormat(DATE_OUTPUT_FORMATS[1]);
        }
        return sdf.format(getDate(str).get());
    }

    // return format of date String or invalid

    /**
     * Returns format of String input of a date.
     *
     * @param str String representation of a date/
     * @return Format of Date object if input is valid. Returns "invalid" otherwise.
     */
    public String getDateFormat(String str) {
        if (str.length() != DATE_INPUT_FORMATS[1].length() &&
                str.length() != DATE_INPUT_FORMATS[3].length()) {
            // input string has invalid format if it is not of correct length
            return DATE_INPUT_FORMATS[0];
        }

        if (str.length() == DATE_INPUT_FORMATS[1].length()) {
            // either of format dd-mm-yyyy or dd/mm/yyyy
            if (str.substring(2, 3).equals("-")) {
                // case: dd-mm-yyyy
                String[] date = str.split("-");
                return (isValidDateNumerals(date)) ? DATE_INPUT_FORMATS[1] : DATE_INPUT_FORMATS[0];
            } else if (str.substring(2, 3).equals("/")) {
                // case: dd/mm/yyyy
                String[] date = str.split("/");
                return (isValidDateNumerals(date)) ? DATE_INPUT_FORMATS[2] : DATE_INPUT_FORMATS[0];
            } else {
                // invalid format
                return DATE_INPUT_FORMATS[0];
            }
        } else {
            // case: dd-mm-yyyy hhhh or dd/mm/yyyy hhhh
            if (str.substring(2, 3).equals("-")) {
                // case: dd-mm-yyyy hhhh
                String hrs = str.split(" ")[1];
                String[] date = str.split(" ")[0].split("-");
                String[] dateTime = new String[4];

                // assign values to dateTime array
                for (int i = 0; i < 3; i++) {
                    dateTime[i] = date[i];
                }
                dateTime[3] = hrs;
                return (isValidDateNumerals(dateTime)) ? DATE_INPUT_FORMATS[3] : DATE_INPUT_FORMATS[0];
            } else if (str.substring(2, 3).equals("/")) {
                // case: dd/mm/yyyy hhhh
                String hrs = str.split(" ")[1];
                String[] date = str.split(" ")[0].split("/");
                String[] dateTime = new String[4];

                // assign values to dateTime array
                for (int i = 0; i < 3; i++) {
                    dateTime[i] = date[i];
                }
                dateTime[3] = hrs;
                return (isValidDateNumerals(dateTime)) ? DATE_INPUT_FORMATS[4] : DATE_INPUT_FORMATS[0];
            } else {
                // invalid format
                return DATE_INPUT_FORMATS[0];
            }
        }

    }

    // checks validity integers in String representation of date

    /**
     * Checks validity of integers in String representation of a date.
     *
     * @param arr Array containing integers for day, month and year.
     * @return True if integers form a valid date. False otherwise.
     */
    private boolean isValidDateNumerals(String[] arr) {
        boolean isValid = true;
        if (Integer.parseInt(arr[0]) > 31) {
            // invalid if day > 31
            isValid = false;
        }
        if (Integer.parseInt(arr[1]) > 12) {
            // invalid if month > 12
            isValid = false;
        }
        if (Integer.parseInt(arr[2]) < 2020) {
            // invalid if year < 2020
            isValid = false;
        }

        if (arr.length == 4) {
            // case dd mm yyyy hhhh
            if (Integer.parseInt(arr[3]) > 2359 || Integer.parseInt(arr[3]) < 0) {
                isValid = false;
            }
        }

        return isValid;
    }
}