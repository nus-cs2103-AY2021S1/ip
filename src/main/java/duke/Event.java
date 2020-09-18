package duke;

public class Event extends Task {

    Event(String name, String at) {
        super(name, at);
    }

    @Override
    String getIndicator() {
        return "[E]";
    }

}
