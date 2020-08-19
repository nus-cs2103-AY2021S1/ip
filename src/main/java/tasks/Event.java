package tasks;

public class Event extends Task {

    // event held at this time
    private final String at;

    public Event(String desc, String at) {
        super(desc);
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
}
