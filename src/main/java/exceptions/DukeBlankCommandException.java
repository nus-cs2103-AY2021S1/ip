package exceptions;

/**
 * Error Type of a Blank Command in Duke Application.
 */
public class DukeBlankCommandException extends DukeException{
    public DukeBlankCommandException(String s){
        super(s,4);
    }
}
