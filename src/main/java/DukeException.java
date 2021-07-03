public class DukeException extends Exception {
    DukeException() {
        super(String.format("%s OOPS!!! I'm sorry, but I don't know what that means.",
                Character.toString(2639)));
    }

    DukeException(String message) {
        super(message);
    }
}
