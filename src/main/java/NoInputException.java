package main.java;

public class NoInputException extends Exception{

    @Override
    public String getMessage() {
        return "No input detected.";
    }
}
