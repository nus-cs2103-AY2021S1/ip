package duke;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public InvalidInputException(String s) {
        super(s);
    }
}
