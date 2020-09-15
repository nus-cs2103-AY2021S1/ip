/**
 * Thrown to indicate that there is no matches with what the user has keyed in.
 */
public class DukeNoMatchesExcpetion extends DukeException {

    /**
     * Constructs a DukeNoMatchesException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeNoMatchesExcpetion(String s) {
        super("OOPS!!! No matches found!");
    }
}
