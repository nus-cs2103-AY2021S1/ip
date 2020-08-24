package main.java;

public class BobIOException extends BobException {
    @Override
    public String getMessage() {
        return "Sorry, but I had difficulty saving your information. Please try again.";
    }
}
