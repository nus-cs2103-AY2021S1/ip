public class DukeIllegalArgumentException extends DukeException {
    
    DukeIllegalArgumentException(String message, DukeExceptionType dukeExceptionType) {
        super(message, dukeExceptionType);
    }
    
    @Override
    public String toString() {
        String message;
        switch (dukeExceptionType) {
            case TODO:
                message = "todo";
                return String.format("OOPS!!! The description of a %s cannot be empty.", message);
            case EVENT:
                message = "event";
                return String.format("OOPS!!! The description of a %s cannot be empty.", message);
            case DEADLINE:
                message = "deadline";
                return String.format("OOPS!!! The description of a %s cannot be empty.", message);
            default:
                return "OOPS!!! The description cannot be empty.";
        }
    }
}
