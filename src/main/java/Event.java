public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event.
     *
     * @param description Event task description
     * @param at          Time frame for event.
     **/
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getInitial() + "]" + super.toString() + " (at:" + at + ")";
    }
}
