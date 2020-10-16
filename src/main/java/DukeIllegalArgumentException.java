/**
 * DukeIllegalArgumentException is thrown when user input is incomplete
 */
public class DukeIllegalArgumentException extends DukeException {

    /**
     * Constructor that creates a DukeIllegalArgumentException.
     * @param message the error message of the exception.
     * @param dukeExceptionType the type of Exception.
     */
    DukeIllegalArgumentException(String message, DukeExceptionType dukeExceptionType) {
        super(message, dukeExceptionType);
    }
    
    @Override
    public String toString() {
        String message;
        switch (dukeExceptionType) {
        case TODO:
            message = "todo command";
            return String.format("I can't hear youuuuuuuu! The description of a %s cannot be empty.", message);
        case EVENT:
            message = "event command";
            return String.format("I can't hear youuuuuuuu! The description of a %s cannot be empty.", message);
        case DEADLINE:
            message = "deadline command";
            return String.format("I can't hear youuuuuuuu! The description of a %s cannot be empty.", message);
        case VIEW_SCHEDULE:
            message = "view schedule command";
            return String.format("I can't hear youuuuuuuu! The description of a %s cannot be empty.", message);
        case DONE:
            message = "done command";
            return String.format("I can't hear youuuuuuuu! The description of a %s cannot be empty.", message);
        case DELETE:
            message = "delete command";
            return String.format("I can't hear youuuuuuuu! The description of a %s cannot be empty.", message);
        case FIND:
            message = "find command";
            return String.format("I can't hear youuuuuuuu! The description of a %s cannot be empty.", message);
        default:
            return "I can't hear youuuuuuuu! The description cannot be empty.";
        }
    }
}
