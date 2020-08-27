import java.lang.Throwable;

public class MissingDescriptionException extends DukeException{

    @Override
    public String getMessage() {
        return "     ☹ OOPS!!! Your command needs to have a description. :-(";
    }
}