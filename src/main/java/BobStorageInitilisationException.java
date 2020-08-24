package main.java;

public class BobStorageInitilisationException extends BobException {
    @Override
    public String getMessage() {
        return "Could not initialise storage";
    }
}
