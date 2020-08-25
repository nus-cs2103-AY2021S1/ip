/** Thrown to indicate that the user has put in an invalid delete command. */
public class DukeDeleteException extends DukeException {

    /** Constructs a DukeDeleteException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeDeleteException(String s){
        super(s);
    }
}
