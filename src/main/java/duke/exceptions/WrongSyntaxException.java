package duke.exceptions;

/**
 * Exception thrown when user input to create Command has wrong syntax.
 */
public class WrongSyntaxException extends DukeException {

    public WrongSyntaxException() {
        super("Syntax error",
                "OOPS!!! There is something wrong with the command, please check the syntax again!");
    }
}
