package tasks;

import exceptions.DataException;

public class Event extends Task {

    // event held at this time
    private final String at;

    public Event(String desc, String at) throws DataException {
        super(desc);
        if (at.isBlank()) {
            throw new DataException("Event Time", "Cannot be blank");
        }
        this.at = at;
    }

    @Override
    protected char getTaskType() {
        return 'E';
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), at);
    }

    @Override
    public String getParentCommand() {
        return "event " + getDescription() + " /at " + at;
    }
}
