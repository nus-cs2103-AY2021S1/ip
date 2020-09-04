package duke.exceptions;

public class DukeException extends Exception {
    
    protected String exceptionMessage;
    
    public DukeException (String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
