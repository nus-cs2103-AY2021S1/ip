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
                message = "todo";
                return String.format("I can't hear youuuuuuuu! The description of a %s cannot be empty.", message);
            case EVENT:
                message = "event";
                return String.format("I can't hear youuuuuuuu! The description of a %s cannot be empty.", message);
            case DEADLINE:
                message = "deadline";
                return String.format("I can't hear youuuuuuuu! The description of a %s cannot be empty.", message);
            default:
                return "I can't hear youuuuuuuu! The description cannot be empty.";
        }
    }
}
