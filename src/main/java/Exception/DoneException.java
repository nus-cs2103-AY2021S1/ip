package Exception;


/**
 * Acts as a parent to exception thrown by DoneCommand.
 */
public class DoneException extends DukeException {
    @Override
    public String toString(){
        return "Done Exception";
    }
}
