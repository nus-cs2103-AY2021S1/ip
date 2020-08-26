package exceptions;

public class InvalidFindFormatException extends DukeException{
    public InvalidFindFormatException() {
        super("☹ OOPS!!! The format of find command seems to be wrong.\n"
                + "Try the following format: find keyword");
    }
}
