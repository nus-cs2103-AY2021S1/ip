package exceptions;

public class DukeException extends Exception {

    DukeException(String message) {
        super("you n00b!\n" + message);
    }

}
