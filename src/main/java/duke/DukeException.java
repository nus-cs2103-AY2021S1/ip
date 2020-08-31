package duke;

/**
 * Wrapper exception for exceptions thrown when
 * Duke program is run.
 */
class DukeException extends Exception {

    private String errorMsg;

    DukeException(String msg) {
        this.errorMsg = msg;
    }

    @Override
    public String toString() {
        return errorMsg;
    }
}
