package Exception;

/**
 * Represents the exception that the value given is not integer.
 */
public class DeleteUnknownException extends DukeException {
    @Override
    public String toString(){
        String s = "Please provide the number of the task to be deleted\n";
        return s;
    }
}
