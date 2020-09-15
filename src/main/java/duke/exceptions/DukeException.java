package duke.exceptions;

/**
 * Represents the exception class in the Duke application, that allows
 * the other exceptions to extend from.
 */
public class DukeException extends Exception{

    public DukeException(String message){
        super(message);
    }
    public String toString(){
        return "Please use the appropriate commands" ;
    }
}
