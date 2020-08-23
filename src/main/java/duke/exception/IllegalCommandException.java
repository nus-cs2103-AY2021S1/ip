package duke.exception;

public class IllegalCommandException extends Exception {
    public IllegalCommandException(String command) {
        super("☹ OOPS!!! I cannot recognise the command \"" + command + "\" :-(");
    }
}
