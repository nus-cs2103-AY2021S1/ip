public class EventWrongFormatException extends WrongFormatException {

    public EventWrongFormatException() {
        super("event");
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "description of a task in the " +
                "following format:\nevent /at time.";
    }
}
