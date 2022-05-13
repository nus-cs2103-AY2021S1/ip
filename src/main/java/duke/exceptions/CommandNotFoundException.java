package duke.exceptions;

public class CommandNotFoundException extends DukeException {
    public CommandNotFoundException(String err) {
        super(err);
    }

    @Override
    public String getMessage() {
        return "🙈 OOPS!!! \n"
                + "Sorry I don't understand what that means";
    }
}
