package main.java;

/**
 * An exception that may be thrown when an error occurs during the parsing of dates and times.
 */
public class BobDateTimeParseException extends BobException {

    /**
     * Returns a message which indicates the correct format of dates and times.
     *
     * @return a message with the correct format of dates and times.
     */
    @Override
    public String getMessage() {
        return "Please input dates and times in the correct format. The format is: "
                + "yyyy-MM-dd HHMM"
                + "Note: Events require a start date and time and an end date and time with the following format:"
                + "yyyy-MM-dd HHMM to yyyy-MM-dd HHMM";
    }
}
