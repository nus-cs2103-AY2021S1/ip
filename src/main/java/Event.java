public class Event extends Task {

    protected static final String TASK_TYPE = "E";

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getSaveFormat() {
        return String.format("%s | %s | %s", Event.TASK_TYPE, super.getSaveFormat(), this.at);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Deadline.TASK_TYPE, super.toString(), this.at);
    }
}