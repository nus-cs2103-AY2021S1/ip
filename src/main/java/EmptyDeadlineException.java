public class EmptyDeadlineException extends EmptyMessageException {
    public EmptyDeadlineException() {
        super(false, "     ☹ OOPS!!! You need to indicate the deadline\n"
                + "       with the \"/by\" command.");
    }
}
