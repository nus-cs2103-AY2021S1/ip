package exceptions;

/**
 * Error Type of a Invalid Command in Duke Application.
 */
public class DukeCommandException extends DukeException{
    /**
     * Constructor class for DukeCommandException
     * @param bad_cmd the command that is unrecognisable by Duke Application
     */
    public DukeCommandException(String bad_cmd){
        super(bad_cmd, 1);
    }
}
