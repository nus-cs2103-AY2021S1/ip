class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException(String msg) {
        super("An invalid deadline was provided: " + msg + ". Please ensure deadlines have the format yyyy-mm-dd.");
    }
}
