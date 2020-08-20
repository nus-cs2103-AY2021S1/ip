public class DukeException extends Exception {

    String message;

    DukeException(String input) {
        super(input);
        message = input;
    }

    @Override
    public String toString() {
        return "Apple Pineapple!! " + message;
    }
}
