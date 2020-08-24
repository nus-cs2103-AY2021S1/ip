package main.java;

public class BobIndexOutOfBoundsException extends BobException {
    @Override
    public String getMessage() {
        return "There are no tasks on the list with the provided index. Please check the list and try again!";
    }
}
