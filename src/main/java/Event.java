public class Event extends Task {
    private String at;

    Event(String description, String at) {
        this(description, false, at);
    }

    Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Return updated task of subtype: Event
     *
     * @param isDone New status for the task
     * @return new Event with updated status
     */
    @Override
    public Task updateStatus(boolean isDone) {
        return new Event(super.description, isDone, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}