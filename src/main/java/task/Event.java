package task;

import exceptions.InvalidDescriptionException;
import exceptions.MissingTimingException;
import task.Task;

public class Event extends Task {
    public Event(String s) throws MissingTimingException, InvalidDescriptionException {
        super(s);
        if (s.isBlank()) {
            throw new InvalidDescriptionException("Please add a description for your event!");
        } else if (!s.contains("/")) {
            throw new MissingTimingException("Don't forget to add a timing for your event!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString().replace("/at", "(at:") + ")";
    }
}
