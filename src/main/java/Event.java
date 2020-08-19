public class Event extends Task {

    Event(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
