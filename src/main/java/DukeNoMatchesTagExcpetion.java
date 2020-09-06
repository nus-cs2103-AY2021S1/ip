public class DukeNoMatchesTagExcpetion extends DukeException {

    /**
     * Constructs a DukeNoMatchesException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeNoMatchesTagExcpetion(String s) {
        super("OOPS!!! No matches found for the Tag!");
    }
}
