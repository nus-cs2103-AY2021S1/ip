class DukeException extends Exception {

    DukeException(String message) {
        super("Action failed: " + message);
    }
}
