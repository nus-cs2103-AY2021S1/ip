package exceptions;

public class DukeException extends Exception {

    // default error message + particular error message
    public DukeException(String message) {
        super("[!] OOPS!!! " + message);
    }
}
