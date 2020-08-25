/** Thrown to indicate that the user has put in an invalid command. */
public class DukeUnknownInputException extends DukeException{

    /** Constructs a DukeUnknownInputException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeUnknownInputException(String s){
        super(s);
    }

}
