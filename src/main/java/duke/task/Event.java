package duke.task;

import duke.exception.DukeException;

public class Event extends TimeTask {
    public Event(String description, String time) throws DukeException {
        super(description, time);
    }
    @Override
    public String getTaskIdentifier() {
        return "E";
    }
    @Override
    public String getDateDescriptor() {
        return "at";
    }
}
