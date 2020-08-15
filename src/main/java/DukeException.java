public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        String message = "\t____________________________________________________________\n"
                + "\t :-( OOPS!!! %s\n"
                + "\t____________________________________________________________\n";
        return String.format(message, super.getMessage());
    }
}
