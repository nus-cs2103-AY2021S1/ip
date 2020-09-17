/**
 * Class to hold the InvalidNumberFromDoneCommandException message of a InvalidNumberFromDoneCommandException.
 */
public class InvalidNumberFromCommandException extends DukeException {

    /**
     * Custom message of a InvalidNumberFromDoneCommandException, reminding the user to type in a valid number
     * for a done command.
     */
    @Override
    public String getMessage() {
        return "     ☹ OOPS!!! The done command must be followed by a valid task number. :-(";
    }
}
