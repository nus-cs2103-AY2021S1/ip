public class MissingTagException extends DukeException {

    @Override
    public String getMessage() {
        return "     ☹ OOPS!!! Your command needs to be followed by a valid tag. :-(\n" +
                "     For the event command, this tag is /at\n" +
                "     For the deadline command, this tag is /by";
    }
}
