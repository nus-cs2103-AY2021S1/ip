/**
 * Allows for Custom Messages to be thrown under DukeException.
 */
public class CustomException extends DukeException{

    CustomException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
