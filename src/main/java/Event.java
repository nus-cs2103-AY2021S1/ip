public class Event extends Task {
    private String at;

    /**
     * Creates an Event task
     * @param task Task to be completed
     * @param at Time when task occurs
     */
    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Event: " + super.getTask() +
                "(at: " + at + ")" + "\n";
    }
}
