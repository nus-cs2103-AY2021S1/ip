/** Thrown to indicate that the user has put in an invalid duke command. */
public class DukeException extends Exception{

    /** Constructs a DukeException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    public DukeException(String s){
        super(s);
    }
}
