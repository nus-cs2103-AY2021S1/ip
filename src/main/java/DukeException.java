public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Return the error message.
     *
     * @return the error message.
     */
    public String getMessage() {
        return "    " + message;
    }

    public String toString() {
        return this.message;
    }
}
