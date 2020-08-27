public class InvalidNumberFromDoneCommandException extends DukeException{

    @Override
    public String getMessage() {
        return "     ☹ OOPS!!! The done command must be followed by a valid task number. :-(";
    }
}
