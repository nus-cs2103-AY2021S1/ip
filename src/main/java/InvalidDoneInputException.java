public class InvalidDoneInputException extends DukeException {

    public InvalidDoneInputException() {
        super("☹ OOPS!!! Invalid input after done command. Keep index within list range. (Example format: done 1)");
    }
}
