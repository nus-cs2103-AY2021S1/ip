package main.java.exception;

public class DukeKeywordException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " You need to specify the keyword.";
    }
}
