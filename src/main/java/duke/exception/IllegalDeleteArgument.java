package duke.exception;

public class IllegalDeleteArgument extends Exception {
    public IllegalDeleteArgument() {
        super("☹ OOPS!!! The argument for a delete command must be an integer within the range!!!");
    }
}