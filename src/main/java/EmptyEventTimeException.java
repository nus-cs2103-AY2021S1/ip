public class EmptyEventTimeException extends EmptyMessageException {
    public EmptyEventTimeException() {
        super(false, "     ☹ OOPS!!! You need to indicate the event\n"
                + "      time with the \"/at\" command.");
    }
}
