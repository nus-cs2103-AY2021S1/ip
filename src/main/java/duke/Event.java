package duke;

public class Event extends Task {
    protected String at;

    /**
     * The constructor of the Event object which is extended from Task{@link Task}. It takes in a task description as
     * well as an "at" String which represents where the event would be at.
     *
     * @param description The description of the Event object
     * @param at where the event would be at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
