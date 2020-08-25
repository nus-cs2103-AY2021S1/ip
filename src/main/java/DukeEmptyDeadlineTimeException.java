/** Thrown to indicate that the user has put in an invalid time for deadline. */
public class DukeEmptyDeadlineTimeException extends DukeException{

    /** Constructs a DukeEmptyDeadlineException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyDeadlineTimeException(String s){
        super(s);
    }
}
