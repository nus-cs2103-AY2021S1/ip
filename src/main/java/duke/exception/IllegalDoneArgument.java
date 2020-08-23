package duke.exception;

public class IllegalDoneArgument extends Exception {
    public IllegalDoneArgument() {
        super("☹ OOPS!!! The argument for a done command must be an integer within the range!!!");
    }
}
