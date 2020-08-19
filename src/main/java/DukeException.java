public class DukeException extends Exception {
    public enum DukeExceptionType {
        TODO,
        EVENT,
        DEADLINE
    }

    DukeExceptionType dukeExceptionType;
    
    public DukeException(String message) {
        super(message);
    }
    
    public DukeException(String message, DukeExceptionType dukeExceptionType) {
        super(message);
        this.dukeExceptionType = dukeExceptionType;
    }
    
    @Override
    public String toString(){
        return String.format("Error: %s", getMessage());
    }
}
