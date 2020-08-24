package main.java.Exception;

public class DescriptionException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " Description cannot be empty";
    }
}
