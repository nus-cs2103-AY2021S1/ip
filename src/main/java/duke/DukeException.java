package duke;

/**
 * DukeException stores all duke-specific exceptions.
 */
public class DukeException extends Exception {

    /** Exception type */
    protected DukeExceptionType exceptionType;
    /** Command type */
    protected DukeCommandType commandType;

    /**
     * Initialises DukeException using exception type.
     *
     * @param e
     * @param exceptionType
     */
    public DukeException(String e, DukeExceptionType exceptionType) {
        super(e);
        this.exceptionType = exceptionType;
    }

    /**
     * Initialises DukeException using exception type and command type.
     *
     * @param e
     * @param exceptionType
     * @param commandType
     */
    public DukeException(String e, DukeExceptionType exceptionType, DukeCommandType commandType) {
        super(e);
        this.exceptionType = exceptionType;
        this.commandType = commandType;
    }

    /**
     * Returns error messages depending on the exception type and command type.
     *
     * @return error messages
     */
    @Override
    public String toString() {
        String error = "";
        switch (exceptionType) {
        case EMPTY_LIST:
//            error += "_____________________________________________________";
            error += "Your list is empty...";
//            error += "\n_____________________________________________________";
            break;
        case UNKNOWN:
//            error += "_____________________________________________________";
            error += "Huh? I don't understand what you mean...";
//            error += "\n_____________________________________________________";
            break;
        case NO_MATCHING_FOUND:
//            error += "_____________________________________________________";
            error += "I can't find anything :-(";
//            error += "\n_____________________________________________________";
            break;
        case WRONG_FORMAT:
            switch (commandType) {
            case DEADLINE:
//                error += "_____________________________________________________";
                error += "Error in adding deadline: Wrong format";
                error += "\nFormat: deadline <description> /by <datetime>";
//                error += "\n_____________________________________________________";
                break;
            case EVENT:
//                error += "_____________________________________________________";
                error += "Error in adding event: Wrong format";
                error += "\nFormat: event <description> /at <datetime>";
//                error += "\n_____________________________________________________";
                break;
            }
            break;
        case MISSING_DESCRIPTION:
            switch (commandType) {
            case TODO:
//                error += "_____________________________________________________";
                error += "Error in adding todo: Missing description";
//                error += "\n_____________________________________________________";
                break;
            case DEADLINE:
//                error += "_____________________________________________________";
                error += "Error in adding deadline: Missing description";
//                error += "\n_____________________________________________________";
                break;
            case EVENT:
//                error += "_____________________________________________________";
                error += "Error in adding event: Missing description";
//                error += "\n_____________________________________________________";
                break;
            }
            break;
        case MISSING_TIMING:
            switch (commandType) {
            case DEADLINE:
//                error += "_____________________________________________________";
                error += "Error in adding deadline: Missing due date";
//                error += "\n_____________________________________________________";
                break;
            case EVENT:
//                error += "_____________________________________________________";
                error += "Error in adding event: Missing scheduled date";
//                error += "\n_____________________________________________________";
                break;
            }
            break;
        case INVALID_INDEX:
            switch (commandType) {
            case DONE:
//                error += "_____________________________________________________";
                error += "Error in marking task done: Invalid index";
//                error += "\n_____________________________________________________";
                break;
            case DELETE:
//                error += "_____________________________________________________";
                error += "Error in deleting task: Invalid index";
//                error += "\n_____________________________________________________";
                break;
            }
            break;
        }
        return error;
    }

    /**
     * Returns error in user's input due to wrong time format.
     */
    public static String wrongTimeFormat() {
        String error = "";
//        error += "_____________________________________________________";
        error += "Error in adding deadline: Wrong format";
        error += "\nFormat: YYYY-MM-DD";
//        error += "\n_____________________________________________________";
        return error;
    }
}