package duke.task;

/**
 * An exception which indicates that the time is invalid.
 */
public class InvalidTimeException extends Exception {

    public InvalidTimeException(String message){
        super(message);
    }

}
