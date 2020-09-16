package duke;

public class UnknownInputException extends Exception {
    public UnknownInputException() {
        super("\n     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
