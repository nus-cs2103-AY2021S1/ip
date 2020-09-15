package duke.exceptions;

import duke.utils.Constants;

public class DurationFormatException extends Exception {
    private DurationErrorType errorType;

    /**
     * Constructor of DurationFormatException.
     * @param errorMessage the error message
     * @param errorType the type of error
     */
    public DurationFormatException(String errorMessage, DurationErrorType errorType) {
        super(errorMessage);
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        switch (this.errorType) {
        case INVALIDINPUTERROR:
            return "The duration format of date is not valid\n"
                    + "please write the duration in the forms of "
                    + Constants.DF_LOCAL_TIME + "-" + Constants.DF_LOCAL_TIME
                    + " or yyyy-mm-dd hh:mm-hh:mm";
        case STARTENDTIMESEQUENCEERROR:
            return "Your start time is after your end time!!";
        default:
            return "Other duration errors";
        }
    }
}
