public class MissingNumberFromCommandException extends DukeException {

    @Override
    public String getMessage() {
        return "     ☹ OOPS!!! Please type in the done command followed by a valid task number. :-(";
    }
}
