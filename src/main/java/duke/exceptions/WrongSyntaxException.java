package duke.exceptions;

public class WrongSyntaxException extends DukeException {

    public WrongSyntaxException() {
        super("Syntax error",
                "OOPS!!! There is something wrong with the command, please check the syntax again!");
    }
}
