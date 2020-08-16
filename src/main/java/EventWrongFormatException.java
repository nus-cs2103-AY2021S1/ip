public class EventWrongFormatException extends WrongFormatException {

    public EventWrongFormatException() {
        super("event");
    }

    @Override
    public String defaultErrorMessage() {
        return super.defaultErrorMessage() + "description of a task in the " +
                "following format:\nevent /at time.";
    }
}
