package task.tasks;

import datetimeconverter.DateTimeConverter;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeConverter.formatDateTime(at) + ")";
    }
}
