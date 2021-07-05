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
            error += "Your list is empty...";
            break;
        case UNKNOWN:
            error += "Huh? I don't understand what you mean...";
            break;
        case NO_MATCHING_FOUND:
            error += "I can't find anything :-(";
            break;
        case WRONG_FORMAT:
            switch (commandType) {
            case DEADLINE:
                error += "Error in adding deadline: Wrong format";
                error += "\nFormat: deadline <description> /by <datetime>";
                break;
            case EVENT:
                error += "Error in adding event: Wrong format";
                error += "\nFormat: event <description> /at <datetime>";
                break;
            case UPDATE:
                error += "Error in updating task: Wrong format";
                error += "\nFormat: update <index> <desc/date> <edited description/date>";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commandType);
            }
            break;
        case WRONG_TIME_FORMAT:
            switch (commandType) {
            case DEADLINE:
                error += "Error in adding deadline: Wrong time format";
                error += "\nFormat: YYYY-MM-DD";
                break;
            case EVENT:
                error += "Error in adding event: Wrong time format";
                error += "\nFormat: YYYY-MM-DD";
                break;
            case UPDATE:
                error += "Error in updating task: Wrong time format";
                error += "\nFormat: YYYY-MM-DD";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commandType);
            }
            break;
        case MISSING_DESCRIPTION:
            switch (commandType) {
            case TODO:
                error += "Error in adding todo: Missing description";
                break;
            case DEADLINE:
                error += "Error in adding deadline: Missing description";
                break;
            case EVENT:
                error += "Error in adding event: Missing description";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commandType);
            }
            break;
        case MISSING_TIMING:
            switch (commandType) {
            case DEADLINE:
                error += "Error in adding deadline: Missing due date";
                break;
            case EVENT:
                error += "Error in adding event: Missing scheduled date";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commandType);
            }
            break;
        case INVALID_INDEX:
            switch (commandType) {
            case DONE:
                error += "Error in marking task done: Invalid index";
                break;
            case DELETE:
                error += "Error in deleting task: Invalid index";
                break;
            case UPDATE:
                error += "Error in updating task: Invalid index";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commandType);
            }
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + exceptionType);
        }
        return error;
    }
}
