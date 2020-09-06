public class DukeEmptyFindTagException extends DukeException {

    /**
     * Constructs a DukeEmptyFindTagException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyFindTagException(String s) {
        super("OOPS!!! You have keyed in an empty tag to find.");
    }
}
