public class Event extends Task {
    Event(String message) {
        super(message);
    }

    @Override
    public String getTypeLetter() {
        // dummy value
        return "[E]";
    }
}
