public class NoDescriptionException extends DukeException {

    public NoDescriptionException(String cmd) {
        super("The description of " + cmd + " cannot be empty lah. Try again!");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
