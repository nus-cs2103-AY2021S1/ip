public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event.
     *
     * @param description Event task description
     * @param at Time frame for event.
     **/
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getStoredString(){
        return "E" + super.toString() + " " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
