package main.java;

public class BobNoDescriptionException extends BobException {
    @Override
    public String getMessage() {
        return "Please include a description for this todo!";
    }
}
