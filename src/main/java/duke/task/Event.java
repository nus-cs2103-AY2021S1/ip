package duke.task;

import java.util.Optional;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getStringType() {
        return "E";
    }

    @Override
    public Optional<String> getDate() {
        return Optional.of(this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
