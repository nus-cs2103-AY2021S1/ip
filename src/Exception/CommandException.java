package Exception;

public class CommandException extends DukeException {
    @Override
    public String toString() {
        String s = "OOPS!!! I don't know what that commands mean.\n";
        return s;
    }
}
