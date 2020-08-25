package duke.task;

import duke.exception.WrongFormatException;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws WrongFormatException {
        super(description, "[E]", "event", false);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) throws WrongFormatException {
        super(description, "[E]", "event", isDone);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String stringToSaveInMemory() {
        return super.stringToSaveInMemory() + "|" + at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}