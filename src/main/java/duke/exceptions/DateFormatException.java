package duke.exceptions;

import duke.utils.Constants;

public class DateFormatException extends DukeException{
    public DateFormatException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "The input format of date is not valid\n"
                + "please write date in the forms of " + Constants.DF_LOCAL_TIME;
    }
}

