package Exceptions;

public class WrongDateTimeFormatException extends DukeException  {

    /**
     * Initializes WrongDateTimeFormatException
     * @param message
     */
    public WrongDateTimeFormatException(String message){
        super(message);
    }
}
