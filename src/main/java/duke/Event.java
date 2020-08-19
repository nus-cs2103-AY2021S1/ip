package duke;

public class Event extends Task {

    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
