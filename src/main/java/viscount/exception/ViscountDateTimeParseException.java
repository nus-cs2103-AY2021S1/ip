package viscount.exception;

/**
 * Represents the exception when the user inputs an incorrectly formatted date time.
 */
public class ViscountDateTimeParseException extends ViscountException {
    private static final String DATE_ERROR_MESSAGE = "Alas, I do not understand. Please format your %s\n"
            + "in this format: dd-MM-yyyy\n"
            + "For example: 23-08-2020";
    
    private static final String DATE_TIME_ERROR_MESSAGE = "Alas, I do not understand. Please format your %s\n"
            + "in this format: dd-MM-yyyy HHmm\n"
            + "For example: 23-08-2020 1300\n"
            + "The time is optional and the default is 0000.";
    
    public ViscountDateTimeParseException(String dateTimeType) {
        super(dateTimeType.equals("date query")
                ? String.format(ViscountDateTimeParseException.DATE_ERROR_MESSAGE, dateTimeType)
                : String.format(ViscountDateTimeParseException.DATE_TIME_ERROR_MESSAGE, dateTimeType));
    }
}
