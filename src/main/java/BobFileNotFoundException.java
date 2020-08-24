package main.java;

public class BobFileNotFoundException extends BobException {
    @Override
    public String getMessage() {
        return "File not found.";
    }
}
