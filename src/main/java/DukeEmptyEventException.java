/** Thrown to indicate that the user has put in an empty event. */
public class DukeEmptyEventException extends DukeException {

    /** Constructs a DukeEmptyEventException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyEventException(String s){
        super(s);
    }
}
