package duke;

public class DukeException extends Exception {

    String message;

    public DukeException(String input) {
        super(input);
        message = input;
    }

    @Override
    public String toString() {
        return "Apple Pineapple!! " + message;
    }
}
