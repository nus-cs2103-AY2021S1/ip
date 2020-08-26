package main.java;

public class NoInputException extends DukeException{

    @Override
    public String getMessage() {
        return " No input detected.";
    }
}
