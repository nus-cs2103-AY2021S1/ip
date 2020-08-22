package duke.tasks;

import duke.utils.DukeDateTime;

public class Event extends Task {

    protected DukeDateTime at;

    public Event(String description, DukeDateTime at) {
        super(description);
        this.at = at;
    }

    public DukeDateTime getAt() {
        return at;
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.toString() + ")";
    }
}
