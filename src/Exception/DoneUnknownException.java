package Exception;

/**
 * Represents the exception that the value given is not integer.
 */
public class DoneUnknownException extends DoneException {
    @Override
    public String toString(){
        String s = "Please provide the number of the task to be marked\n";
        return s;
    }
}
