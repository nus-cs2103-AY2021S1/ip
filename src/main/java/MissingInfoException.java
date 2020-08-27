public class MissingInfoException extends DukeException{

    @Override
    public String getMessage() {
        return "     ☹ OOPS!!! Your command needs to be followed by a description. :-(";
    }
}
