package dd.tasks;

public class Event extends Task {

    protected String at;

    /**
     * Class Constructor.
     *
     * @param description Description of event.
     * @param at Date or date and time of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns date of event.
     *
     * @return Date of event.
     */
    public String getDate() {
        return at.substring(0, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveString() {
        if (this.isDone) {
            return "T , 1 , " + description + " , " + at;
        }
        else {
            return "T , 0 , " + description + " , " + at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
